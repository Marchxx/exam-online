package com.xiyou.common.jwt.service;

import com.xiyou.common.redis.IRedisService;
import com.xiyou.common.jwt.JWTToken;
import com.xiyou.common.jwt.utils.JWTUtil;
import com.xiyou.common.redis.utils.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: attendance
 * @description: JWTTokenService实现类
 * @author: tangcan
 * @create: 2018-12-03 15:00
 **/
@Component
public class JWTTokenService {

    @Autowired
    private IRedisService redisService;

    @Value("${sys.limit-multiple-login}")
    private String LIMIT_MULTIPLE_LOGIN;

    public JWTToken get(String token) {
        String tokenKey = RedisKeyUtil.get("token", token);
        return (JWTToken) redisService.get(tokenKey);
    }

    public void update(JWTToken jwtToken) {
        /*
        同时缓存两个值
        1. user_id -> token
        2. token -> jwtToken
        方便进行登陆限制
         */
        String userIdKey = RedisKeyUtil.get("user_id", String.valueOf(jwtToken.getUserId()));
        String tokenKey = RedisKeyUtil.get("token", jwtToken.getToken());
        redisService.set(userIdKey, jwtToken.getToken(), JWTUtil.ACTIVE_TIME);
        redisService.set(tokenKey, jwtToken, JWTUtil.ACTIVE_TIME);
    }

    public void remove(JWTToken jwtToken) {
        if (jwtToken == null) {
            return;
        }
        // 清除user_id缓存的值
        String userIdKey = RedisKeyUtil.get("user_id", String.valueOf(jwtToken.getUserId()));
        redisService.delete(userIdKey);
        // 清除所有登录状态
        this.deleteAllCache(jwtToken.getToken());
    }

    public void save(JWTToken jwtToken) {
        // 如果允许多端登录
        if ("true".equals(LIMIT_MULTIPLE_LOGIN)) {
            // 获取当前用户之前是否已登录
            String userIdKey = RedisKeyUtil.get("user_id", String.valueOf(jwtToken.getUserId()));
            String oldToken = (String) redisService.get(userIdKey);

            if (oldToken != null) {
                // 如果已登录，先清除之前的登录状态
                this.deleteAllCache(oldToken);
                // 重新缓存登录状态
                redisService.set(userIdKey, jwtToken.getToken(), JWTUtil.ACTIVE_TIME);
            }
        }

        String tokenKey = RedisKeyUtil.get("token", jwtToken.getToken());
        redisService.set(tokenKey, jwtToken, JWTUtil.ACTIVE_TIME);
    }

    /*
    删除所有相关的缓存
     */
    private void deleteAllCache(String token) {
        if (StringUtils.isBlank(token)) {
            return;
        }
        // 先删除token的缓存值
        String oldTokenKey = RedisKeyUtil.get("token", token);
        redisService.delete(oldTokenKey);
        /*
        删除shiro的缓存记录

        shiro在redis中缓存的key值如：
        "shiro:cache:com.xiyou.main.shiro.ShiroRealm.authorizationCache:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"
        "shiro:cache:authenticationCache:JWTToken(token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9, activeTime=null, userId=null, account=null, name=null)"
        因此只需要删除对应key值的缓存，用户登录状态即失效
         */
        // 授权缓存
        String authorizationCacheKey = "shiro:cache:com.xiyou.main.shiro.ShiroRealm.authorizationCache:" + token;
        // 身份验证缓存
        String authenticationCacheKey = "shiro:cache:authenticationCache:" + new JWTToken().setToken(token);
        redisService.delete(authorizationCacheKey);
        redisService.delete(authenticationCacheKey);
    }
}
