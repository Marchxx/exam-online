package com.march.common.utils;

import com.march.main.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    /**
     * 设置过期时间一天,静态域属于整个类
     */
    private static long EXPIRE_TIME = 1000 * 60 * 60 * 24;
    /**
     * 数字签名的私钥
     */
    private static final String APP_KEY = "march_exam";

    /**
     * 签发Token
     * 一个JWT实际上就是一个字符串，它由三部分组成，头部(Header)、载荷(Payload)与签名(Signature)
     *
     * @return
     */
    public static String creatJwt(User user) {
        if (user == null || user.getUserName() == null || user.getUserId() == null) {
            return null;
        }
        return Jwts.builder()
                //下面2行设置token中间字段，携带用户的信息
                .claim("id", user.getUserId())
                .claim("username", user.getUserName())
                .setIssuedAt(new Date())
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, APP_KEY)
                .compact();
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(APP_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 篡改token会导致校验失败，走到异常分支，这里返回null
            return null;
        }
    }
}
