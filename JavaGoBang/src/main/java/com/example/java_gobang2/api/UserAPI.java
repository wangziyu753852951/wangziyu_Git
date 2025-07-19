package com.example.java_gobang2.api;

/**
 * ClassName: UserApi
 * Description
 *
 * @Author wzy
 * @Create 2025/3/19 16:19
 * @Version 1.0
 */

import com.example.java_gobang2.model.User;
import com.example.java_gobang2.model.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserAPI {
    @Resource
    private UserMapper userMapper;
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest req) {
        User user = userMapper.selectByName(username);
        System.out.println("[login] username=" + username);
        if (user == null || !user.getPassword().equals(password)) {
        System.out.println("登陆失败!");
        return new User();
    }
        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("user", user);
        return user;
    }

    @PostMapping("/register")
    @ResponseBody
    public Object register(String username, String password) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insert(user);
            return user;
        } catch (org.springframework.dao.DuplicateKeyException e) {
            User user = new User();
            return user;
        }
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Object getUserInfo(HttpServletRequest req) {
        try {
            //当前从服务器拿信息的对象不是数据库的最新对象，而是登录过程中往session存的user对象
            HttpSession httpSession = req.getSession(false);
            User user = (User) httpSession.getAttribute("user");
            //所以需要保证从服务器拿到的数据必须是最新的对象
            User newUser = userMapper.selectByName(user.getUsername());
            return newUser;
        } catch (NullPointerException e) {
            return new User();
        }
    }
}
