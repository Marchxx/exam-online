package com.xiyou.common.config;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * @program: multi-module
 * @description: 解决mybatis返回类型为Map时，key值不是驼峰命名的问题
 * @author: tangcan
 * @create: 2019-07-14 16:34
 **/
public class CustomMapWrapper extends MapWrapper {
    public CustomMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if (useCamelCaseMapping) {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
        }
        return name;
    }
}
