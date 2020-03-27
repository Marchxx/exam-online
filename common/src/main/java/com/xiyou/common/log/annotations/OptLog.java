package com.xiyou.common.log.annotations;

import java.lang.annotation.*;

@Target(value = {ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {
    String description() default "";
}
