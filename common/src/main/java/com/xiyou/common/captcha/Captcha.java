package com.xiyou.common.captcha;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: multi-module
 * @description:
 * @author: tangcan
 * @create: 2019-07-05 18:01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Captcha {
    /*
    验证码
     */
    private String verCode;
    /*
    图片数据
     */
    private String base64;
    /*
    缓存的key值
     */
    private String verKey;
}
