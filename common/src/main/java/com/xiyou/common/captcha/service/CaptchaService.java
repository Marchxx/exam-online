package com.xiyou.common.captcha.service;

import com.wf.captcha.SpecCaptcha;
import com.xiyou.common.captcha.Captcha;
import com.xiyou.common.enums.CodeEnum;
import com.xiyou.common.exception.CustomException;
import com.xiyou.common.redis.IRedisService;
import com.xiyou.common.redis.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description:
 * @author: tangcan
 * @create: 2019-04-26 17:07
 **/
@Component
public class CaptchaService {
    @Autowired
    private IRedisService redisService;


    public Captcha getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        redisService.set(RedisKeyUtil.get("captcha", key), verCode, 5 * 60);
        return new Captcha().setVerKey(key).setBase64(specCaptcha.toBase64()).setVerCode(verCode);
    }

    public void verify(String verKey, String verCode) {
        if (verCode == null) {
            throw new CustomException(CodeEnum.CODE_ERROR);
        }
        String code = (String) redisService.get(RedisKeyUtil.get("captcha", verKey));
        if (code == null) {
            redisService.delete(RedisKeyUtil.get("captcha", verKey));
            throw new CustomException(CodeEnum.CODE_EXPIRE);
        }
        if (!code.equals(verCode.trim().toLowerCase())) {
            throw new CustomException(CodeEnum.CODE_ERROR);
        }
        redisService.delete(RedisKeyUtil.get("captcha", verKey));
    }
}
