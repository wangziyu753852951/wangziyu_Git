package com.example.java_gobang2.model;

import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Description
 *
 * @Author wzy
 * @Create 2025/2/13 21:31
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    void insert(User user);

    User selectByName(String username);

    void userWin(int userId);

    void userLose(int userId);
}
