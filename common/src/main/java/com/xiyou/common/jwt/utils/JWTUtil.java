package com.xiyou.common.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @program: attendance
 * @description: JWT工具
 * @author: tangcan
 * @create: 2018-12-03 15:24
 **/
public class JWTUtil {
    /**
     * 活跃过期时间：24*60分钟
     */
    public static final long ACTIVE_TIME = 24 * 60 * 60;
    /**
     * claim
     */
    private static final String CLAIM_account = "account";
    private static final String USER_ID = "user_id";

    /**
     * 生成签名
     *
     * @param account 用户
     * @param secret  用户的密码
     * @return 加密的token
     */
    public static String sign(String account, Integer userId, String secret) {
        Date date = new Date(System.currentTimeMillis() + ACTIVE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
            /*
            附带id信息
             */
        return JWT.create()
                // 账号
                .withClaim(CLAIM_account, account)
                .withClaim(USER_ID, userId)
                // 到期时间
                .withExpiresAt(date)
                // 创建一个新的JWT，并使用给定的算法进行标记
                .sign(algorithm);
    }

    public static boolean verify(String token, String password) {
        String account = getAccount(token);
        Integer userId = getUserId(token);
        if (account == null) {
            return false;
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(CLAIM_account, account)
                    .withClaim(USER_ID, userId)
                    .build();
            /*
            验证token
             */
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @Author: tangcan
     * @Description: 验证登陆是否还有效
     * @Param: [token]
     * @date: 2019/1/12
     */
    public static boolean verifyActive(String token) {
        if (token == null) {
            return false;
        }
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        return expiresAt != null && expiresAt.compareTo(new Date()) >= 0;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的账号
     */
    public static String getAccount(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(CLAIM_account).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserId(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(USER_ID).asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
