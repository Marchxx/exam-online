package com.xiyou.common.jwt;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.shiro.authc.AuthenticationToken;

import java.time.LocalDateTime;

/**
 * @program: attendance
 * @description:
 * @author: tangcan
 * @create: 2018-12-03 14:54
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JWTToken implements AuthenticationToken {
    /*
     * 密钥
     */
    private String token;
    /*
    过期时间
     */
    private LocalDateTime expireTime;
    /*
    用户id
     */
    private Integer userId;
    /*
    账号
     */
    private String account;
    /*
    姓名
     */
    private String name;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
