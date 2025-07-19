package com.example.java_gobang2.api;

import com.example.java_gobang2.game.*;
import com.example.java_gobang2.model.User;
import com.example.java_gobang2.model.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * ClassName: GameAPI
 * Description
 *
 * @Author wzy
 * @Create 2025/3/22 16:27
 * @Version 1.0
 */
@Component
public class GameAPI extends TextWebSocketHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RoomManager roomManager;

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Resource
    private UserMapper userMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        GameReadyResponse resp = new GameReadyResponse();


        User user = (User) session.getAttributes().get("user");
        if (user == null) {
            resp.setOk(false);
            resp.setReason("用户尚未登录!");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(resp)));
            return;
        }


        Room room = roomManager.getRoomByUserId(user.getUserId());
        if (room == null) {

            resp.setOk(false);
            resp.setReason("用户尚未匹配到!");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(resp)));
            return;
        }

        if (onlineUserManager.getFromGameHall(user.getUserId()) != null
                || onlineUserManager.getFromGameRoom(user.getUserId()) != null) {

            resp.setOk(true);
            resp.setReason("禁止多开游戏页面");
            resp.setMessage("repeatConnection");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(resp)));
            return;
        }


        onlineUserManager.enterGameRoom(user.getUserId(), session);

        if(room.getUser2().getUserId()==-1){
            System.out.println("进入");
            GameReadyResponse gameReadyResponse = new GameReadyResponse();
            gameReadyResponse.setMessage("gameReady");
            gameReadyResponse.setOk(true);
            gameReadyResponse.setReason("hhhhh");
            gameReadyResponse.setRoomId(room.getRoomId());
            gameReadyResponse.setThisUserId(user.getUserId());
            gameReadyResponse.setThatUserId(roomManager.getRoomByUserId(user.getUserId()).getUser2().getUserId());
            gameReadyResponse.setWhiteUser(room.getWhiteUser());
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(gameReadyResponse)));
            System.out.println("出去");
            return;
        }
        synchronized (room) {
            if (room.getUser1() == null) {

                room.setUser1(user);
                room.setWhiteUser(user.getUserId());
                System.out.println("玩家 " + user.getUsername() + " 已经准备就绪! 作为玩家1");
                return;
            }
            if (room.getUser2() == null) {
                room.setUser2(user);
                System.out.println("玩家 " + user.getUsername() + " 已经准备就绪! 作为玩家2");

                noticeGameReady(room, room.getUser1(), room.getUser2());
                noticeGameReady(room, room.getUser2(), room.getUser1());
                return;
            }
        }


        resp.setOk(false);
        resp.setReason("当前房间已满, 您不能加入房间");
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(resp)));
    }

    private void noticeGameReady(Room room, User thisUser, User thatUser) throws IOException {
        GameReadyResponse resp = new GameReadyResponse();
        resp.setMessage("gameReady");
        resp.setOk(true);
        resp.setReason("");
        resp.setRoomId(room.getRoomId());
        resp.setThisUserId(thisUser.getUserId());
        resp.setThatUserId(thatUser.getUserId());
        resp.setWhiteUser(room.getWhiteUser());
        WebSocketSession webSocketSession = onlineUserManager.getFromGameRoom(thisUser.getUserId());
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(resp)));
    }



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //获取user
        User user = (User) session.getAttributes().get("user");
        if(user == null){
            System.out.println("handleTextMessage 当前玩家尚未登录！");
            return;
        }
        //获取room，执行putchess
        Room room = roomManager.getRoomByUserId(user.getUserId());
        room.putChess(message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        User user = (User) session.getAttributes().get("user");
        if(user == null)return;
        WebSocketSession exitSession = onlineUserManager.getFromGameRoom(user.getUserId());
        if(exitSession == session)onlineUserManager.exitGameRoom(user.getUserId());
        System.out.println("当前用户 " + user.getUsername() + " 游戏房间连接异常!");
        noticeThatUserWin(user);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = (User) session.getAttributes().get("user");
        if(user == null)return;
        WebSocketSession exitSession = onlineUserManager.getFromGameRoom(user.getUserId());
        if(exitSession == session)onlineUserManager.exitGameRoom(user.getUserId());
        System.out.println("当前用户 " + user.getUsername() + " 离开游戏房间!");
        noticeThatUserWin(user);
    }

    private void noticeThatUserWin(User user) throws IOException{
        Room room = roomManager.getRoomByUserId(user.getUserId());
        if(room == null){
            System.out.println("房间不存在，游戏已结束！");
            return;
        }
        User thatUser = room.getUser1() == user?room.getUser2():room.getUser1();
        WebSocketSession webSocketSession = onlineUserManager.getFromGameRoom(thatUser.getUserId());
        if(webSocketSession == null){
            System.out.println("对手也下线了！");
            return;
        }
        GameResponse gameResponse = new GameResponse();
        gameResponse.setWinner(thatUser.getUserId());
        gameResponse.setUserId(thatUser.getUserId());
        gameResponse.setMessage("putChess");
        webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(gameResponse)));
        //更新玩家分数信息
        int winUserId = thatUser.getUserId();
        int loseUserId = user.getUserId();
        userMapper.userWin(winUserId);
        userMapper.userLose(loseUserId);
        //释放房间
        roomManager.remove(room.getRoomId(),room.getUser1().getUserId(),room.getUser2().getUserId());
    }
}
