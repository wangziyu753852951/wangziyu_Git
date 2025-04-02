package com.example.java_gobang2.api;

import com.example.java_gobang2.api.Matcher;
import com.example.java_gobang2.game.*;
import com.example.java_gobang2.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MatchAPI extends TextWebSocketHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OnlineUserManager onlineUserManager;

    @Autowired
    private Matcher matcher;

    @Autowired
    private RoomManager roomManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 玩家上线, 加入到 OnlineUserManager 中

        // 1. 先获取到当前用户的身份信息(谁在游戏大厅中, 建立的连接)
        //    此处的代码, 之所以能够 getAttributes, 全靠了在注册 Websocket 的时候,
        //    加上的 .addInterceptors(new HttpSessionHandshakeInterceptor());
        //    这个逻辑就把 HttpSession 中的 Attribute 都给拿到 WebSocketSession 中了
        //    在 Http 登录逻辑中, 往 HttpSession 中存了 User 数据: httpSession.setAttribute("user", user);
        //    此时就可以在 WebSocketSession 中把之前 HttpSession 里存的 User 对象给拿到了.
        //    注意, 此处拿到的 user, 是有可能为空的!!
        //    如果之前用户压根就没有通过 HTTP 来进行登录, 直接就通过 /game_hall.html 这个 url 来访问游戏大厅页面
        //    此时就会出现 user 为 null 的情况
        try {
            User user = (User) session.getAttributes().get("user");
            System.out.println("Setting user in session: " + user.getUsername());


            // 2. 先判定当前用户是否已经登录过(已经是在线状态), 如果是已经在线, 就不该继续进行后续逻辑.
            if (onlineUserManager.getFromGameHall(user.getUserId()) != null
                    || onlineUserManager.getFromGameRoom(user.getUserId()) != null) {
                // 当前用户已经登录了!!
                // 针对这个情况要告知客户端, 你这里重复登录了.
                MatchResponse response = new MatchResponse();
                response.setOk(true);
                response.setReason("当前禁止多开!");
                response.setMessage("repeatConnection");
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
                return;
            }

            // 3. 拿到了身份信息之后, 就可以把玩家设置成在线状态了
            onlineUserManager.enterGameHall(user.getUserId(), session);
            System.out.println("玩家 " + user.getUsername() + " 进入游戏大厅!");
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.afterConnectionEstablished] 当前用户未登录!");
            // e.printStackTrace();
            // 出现空指针异常, 说明当前用户的身份信息是空, 用户未登录呢.
            // 把当前用户尚未登录这个信息给返回回去~~
            MatchResponse response = new MatchResponse();
            response.setOk(false);
            response.setReason("您尚未登录! 不能进行后续匹配功能!");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 实现处理开始匹配请求和处理停止匹配请求.
        User user = (User) session.getAttributes().get("user");
        // 获取到客户端给服务器发送的数据
        String payload = message.getPayload();
        // 当前这个数据载荷是一个 JSON 格式的字符串, 就需要把它转成 Java 对象. MatchRequest
        MatchRequest request = objectMapper.readValue(payload, MatchRequest.class);
        MatchResponse response = new MatchResponse();
        if (request.getMessage().equals("startMatch")) {
            // 进入匹配队列
            matcher.addUser(user);
            // 把玩家信息放入匹配队列之后, 就可以返回一个响应给客户端了.
            response.setOk(true);
            response.setMessage("startMatch");
        } else if (request.getMessage().equals("aiMatch")) {
            // 进入人机对战
            Room room = new Room();
            User user1 = user;
            User user2 = new User();  // 创建人机
            user2.setUserId(-1);  // 设置虚拟对手ID为-1
            user2.setUsername("人机");  // 设置虚拟对手用户名

            // 将玩家与人机加入到游戏房间
            roomManager.add(room, user1.getUserId(), user2.getUserId());
//            onlineUserManager.enterGameRoom(user.getUserId(), session);
            room.setUser1(user1);
            room.setWhiteUser(user.getUserId());

            System.out.println("玩家 " + user.getUsername() + " 已经准备就绪! 作为玩家1");

            room.setUser2(user2);
            MatchResponse response1 = new MatchResponse();
            response1.setOk(true);
            response1.setMessage("matchSuccess");

            // 将匹配成功信息发送给客户端
            String json1 = objectMapper.writeValueAsString(response1);
            session.sendMessage(new TextMessage(json1));

//            // 通知游戏准备就绪
//            GameReadyResponse gameReadyResponse = new GameReadyResponse();
//            gameReadyResponse.setMessage("gameReady");
//            gameReadyResponse.setOk(true);
//            gameReadyResponse.setReason("");
//            gameReadyResponse.setRoomId(room.getRoomId());
//            gameReadyResponse.setThisUserId(user1.getUserId());
//            gameReadyResponse.setThatUserId(user2.getUserId());
//            gameReadyResponse.setWhiteUser(room.getWhiteUser());
//
//            WebSocketSession webSocketSession = onlineUserManager.getFromGameRoom(user1.getUserId());
//            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(gameReadyResponse)));
        }
        else {
            response.setOk(false);
            response.setReason("非法的匹配请求");
        }
        String jsonString = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(jsonString));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        try {
            // 玩家下线, 从 OnlineUserManager 中删除
            User user = (User) session.getAttributes().get("user");
            WebSocketSession tmpSession = onlineUserManager.getFromGameHall(user.getUserId());
            if (tmpSession == session) {
                onlineUserManager.exitGameHall(user.getUserId());
            }
            // 如果玩家正在匹配中, 而 websocket 连接断开了, 就应该移除匹配队列
            matcher.removeUser(user);
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.handleTransportError] 当前用户未登录!");
//            e.printStackTrace();

//            MatchResponse response = new MatchResponse();
//            response.setOk(false);
//            response.setReason("您尚未登录! 不能进行后续匹配功能!");
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        try {
            // 玩家下线, 从 OnlineUserManager 中删除
            User user = (User) session.getAttributes().get("user");
            WebSocketSession tmpSession = onlineUserManager.getFromGameHall(user.getUserId());
            if (tmpSession == session) {
                onlineUserManager.exitGameHall(user.getUserId());
            }
            // 如果玩家正在匹配中, 而 websocket 连接断开了, 就应该移除匹配队列
            matcher.removeUser(user);
        } catch (NullPointerException e) {
            System.out.println("[MatchAPI.afterConnectionClosed] 当前用户未登录!");
            // e.printStackTrace();

            // 这个代码之前写的草率了!!
            // 不应该在连接关闭之后, 还尝试发送消息给客户端
//            MatchResponse response = new MatchResponse();
//            response.setOk(false);
//            response.setReason("您尚未登录! 不能进行后续匹配功能!");
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(response)));
        }
    }
}