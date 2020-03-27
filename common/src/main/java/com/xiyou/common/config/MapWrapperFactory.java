package com.xiyou.common.config;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;

/**
 * @program: multi-module
 * @description: 解决mybatis返回类型为Map时，key值不是驼峰命名的问题
 * @author: tangcan
 * @create: 2019-07-14 16:43
 **/
public class MapWrapperFactory implements ObjectWrapperFactory {
    @Override
    public boolean hasWrapperFor(Object o) {
        return o instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object o) {
        return new CustomMapWrapper(metaObject, (Map) o);
    }
}
