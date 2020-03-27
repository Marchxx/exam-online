package com.xiyou.common.log.aop;

import com.xiyou.common.log.annotations.OptLog;
import com.xiyou.common.log.components.SaveUserLog;
import com.xiyou.common.log.entity.UserLog;
import com.xiyou.common.jwt.JWTToken;
import com.xiyou.common.jwt.service.JWTTokenService;
import com.xiyou.common.jwt.utils.ShiroUtil;
import com.xiyou.common.utils.NetworkUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: multi-module
 * @description: 重要日志拦截存库
 * @author: tangcan
 * @create: 2019-07-04 21:10
 **/
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private SaveUserLog saveUserLog;
    @Autowired
    private JWTTokenService jwtTokenService;

    /*
    对使用@UserLog的操作日志进行存库
     */
    @Pointcut("@annotation(com.xiyou.common.log.annotations.OptLog)")
    public void saveLogAspect() {
    }

    @Around("saveLogAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        /*
        获取注解里的值
         */
        Method method = signature.getMethod();
        String description = method.getAnnotation(OptLog.class).description();

        /*
        获取执行结果
         */
        Object proceed = pjp.proceed();
        Map<String, Object> map = (proceed == null ? new HashMap<>() : (Map<String, Object>) proceed);

        /*
        日志存库
         */
        StringBuffer url = request.getRequestURL();
        String param = request.getQueryString();
        if (StringUtils.isNoneBlank(param)) {
            url.append('?').append(param);
        }
        JWTToken jwtToken = ShiroUtil.getJWTToken();
        String account = null;
        String name = null;
        if (jwtToken != null) {
            account = ShiroUtil.getAccount();
            name = ShiroUtil.getName();
        } else {
            // 说明是登录接口，可以从返回的token字段里面获取token
            String token = (String) map.get("token");
            jwtToken = jwtTokenService.get(token);
            if (jwtToken != null) {
                account = jwtToken.getAccount();
                name = jwtToken.getName();
            }
        }

        UserLog userLog = new UserLog()
                .setAccount(account)
                .setName(name)
                .setOperation(description)
                .setOs(NetworkUtil.getOsName(request))
                .setBrowser(NetworkUtil.getBrowserName(request))
                .setResult((String) map.get("msg"))
                .setIp(NetworkUtil.getIpAddress(request))
                .setUrl(url.toString());
        saveUserLog.save(userLog);

        return proceed;
    }
}
