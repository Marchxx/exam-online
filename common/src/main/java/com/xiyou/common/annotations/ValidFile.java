package com.xiyou.common.annotations;

/**
 * @Author: tangcan
 * @Description: 验证文件格式
 * @Param:
 * @date: 2019/6/18
 */

import com.xiyou.common.validator.MultipartFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MultipartFileValidator.class)
public @interface ValidFile {
    /*
    文件格式
     */
    String[] file() default {};

    /*
    是否区分大小写
     */
    boolean ignoreCase() default false;

    /*
    错误信息提示
     */
    String message() default "上传文件无效！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
