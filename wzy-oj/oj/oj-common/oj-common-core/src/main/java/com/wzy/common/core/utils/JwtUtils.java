package com.wzy.common.core.utils;


import com.wzy.common.core.constants.Jwtconstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JwtUtils
 * Description
 *
 * @Author wzy
 * @Create 2025/8/2 14:11
 * @Version 1.0
 */
public class JwtUtils {
    /**
     * ⽣成令牌
     *
     * @param claims 数据
     * @param secret 密钥
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims, String secret)
    {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }
    /**
     * 从令牌中获取数据
     *
     * @param token  令牌
     * @param secret 密钥
     * @return 数据
     */
    public static Claims parseToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        Map<String,Object> claims = new HashMap<>();
        //用户唯一标识
        claims.put("userid",123456789L);
        claims.put("userKey",23123134124L);
        //secret 保密 随机 不能硬编码 定期更换 但测试时就随机给一个了
        System.out.println(createToken(claims, "dashjgdasjdhgafdsa"));
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VySWQiOjEsInVzZXJLZXkiOiJmYWRkY2RmMC00MzZhLTRhNDAtOWVhOC1lMTMwMzczZjRhZGIifQ.3RDHyrMPA8azxHahg_Z9XMmcO6bENMRvt8zl2q30tChpGHz4Joy7Fa8SdOxRP7g41GB8pX2aSHmZHMY6cjvDoQ";
        System.out.println(parseToken(token, "dashjgdasjdhgafdsa"));

        //1.用户登陆成功之后，根据createToken得到token，返回给客户端
        //2.后续所有请求，需要服务器收到token后进行身份验证
        //3.用户使用系统时，需要进行适当延长jwt过期时间
    }

    public static String getUserKey(Claims claims) {
        return toStr(claims.get(Jwtconstants.LOGIN_USER_KEY));
    }

    public static String getUserId(Claims claims) {
        return toStr( claims.get(Jwtconstants.LOGIN_USER_ID));
    }

    private static String toStr(Object value) {
        if(value == null){
            return "";
        }
        return value.toString();
    }
}
