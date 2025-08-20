package com.wzy.system.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * 加密算法⼯具类
 */
public class BCryptUtils {
    /**
     * ⽣成加密后密⽂
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    /**
     * 判断密码是否相同
     *
     * @param rawPassword
    真实密码
     * @param encodedPassword 加密后密⽂
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String
            encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin"));

        System.out.println(matchesPassword("wzy", "$2a$10$ux7Ho7WhJFDBtFWlHcpU2ub2ra2k7qZ2jbMWn6p3fcPqqKdWupPsW"));
    }
}