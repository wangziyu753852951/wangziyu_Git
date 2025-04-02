package com.example.java_gobang2.game;

import com.example.java_gobang2.model.User;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: RoomManager
 * Description
 *
 * @Author wzy
 * @Create 2025/3/21 18:10
 * @Version 1.0
 */
@Component
public class RoomManager {
    ConcurrentHashMap<String,Room> rooms = new ConcurrentHashMap<String,Room>();
    ConcurrentHashMap<Integer,String> userIdToRoomId = new ConcurrentHashMap<Integer,String>();
    public void add(Room room,int userId1,int userId2){
        rooms.put(room.getRoomId(),room);
        userIdToRoomId.put(userId1,room.getRoomId());
        userIdToRoomId.put(userId2,room.getRoomId());
    }
    public void remove(String roomId, int userId1, int userId2) {
        rooms.remove(roomId);
        userIdToRoomId.remove(userId1);
        userIdToRoomId.remove(userId2);
    }

    public Room getRoomByRoomId(String roomId){
        return rooms.get(roomId);
    }
    public Room getRoomByUserId(int userId){
        String roomId = userIdToRoomId.get(userId);
        if(roomId == null)return null;
        return rooms.get(roomId);
    }
}
