package com.xiyou.common.log.aop;

import com.alibaba.fastjson.JSONObject;
import com.xiyou.common.utils.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: attendance
 * @description: 日志记录
 * @author: tangcan
 * @create: 2018-11-26 14:38
 **/
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    /*
    不存库，控制台打印
     */
    @Pointcut("execution(public * com.xiyou.*.controller..*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        /*
          接收到请求
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        log.info("-------------------------------------------");
        /*
          获取真实的ip地址
         */
        log.info("IP 地址 : " + NetworkUtil.getIpAddress(request));
        /*
          记录请求
         */
        log.info("请求方式 : " + request.getMethod());
        log.info("请求接口 : " + request.getRequestURI());
        log.info("调用方法 : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("请求参数 : " + JSONObject.toJSONString(getParamMap(signature.getParameterNames(), pjp.getArgs())));
        long startTime = System.currentTimeMillis();
        /*
          ob 为方法的返回值
         */
        Object ob = pjp.proceed();
        log.info("请求耗时 : " + (System.currentTimeMillis() - startTime) + " ms");
        if (ob != null) {
            log.info("返回字段 : " + ob);
        }
        log.info("-------------------------------------------");
        return ob;
    }

    /**
     * 获取请求的参数
     *
     * @param argNames  参数名
     * @param argValues 参数值
     * @return 参数Map
     */
    private Map<String, String> getParamMap(String[] argNames, Object[] argValues) {
        Map<String, String> paramMap = new HashMap<>();
        int paramSize = argNames.length;
        for (int i = 0; i < paramSize; i++) {
            paramMap.put(argNames[i], String.valueOf(argValues[i]));
        }
        return paramMap;
    }
}
