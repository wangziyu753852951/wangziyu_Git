package com.example.java_gobang2.game;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: OnlineUserManager
 * Description
 *
 * @Author wzy
 * @Create 2025/3/20 22:34
 * @Version 1.0
 */
@Component
public class OnlineUserManager {
    // 这个哈希表就用来表示当前用户在游戏大厅在线状态.
    private ConcurrentHashMap<Integer, WebSocketSession> gameHall = new ConcurrentHashMap<>();
    // 这个哈希表就用来表示当前用户在游戏房间的在线状态.
    private ConcurrentHashMap<Integer, WebSocketSession> gameRoom = new ConcurrentHashMap<>();

    public void enterGameHall(int userId, WebSocketSession webSocketSession) {
        gameHall.put(userId, webSocketSession);
    }

    public void exitGameHall(int userId) {
        gameHall.remove(userId);
    }

    public WebSocketSession getFromGameHall(int userId) {
        return gameHall.get(userId);
    }

    public void enterGameRoom(int userId, WebSocketSession webSocketSession) {
        gameRoom.put(userId, webSocketSession);
    }

    public void exitGameRoom(int userId) {
        gameRoom.remove(userId);
    }

    public WebSocketSession getFromGameRoom(int userId) {
        return gameRoom.get(userId);
    }

    public WebSocketSession getFormGameHall(int userId) {
        return gameHall.get(userId);
    }
}
