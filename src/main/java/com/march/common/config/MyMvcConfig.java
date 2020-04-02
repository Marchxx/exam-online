package com.march.common.config;

import com.march.common.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加的页面都会经过拦截器，并将对应参数放到请求中
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/user/**")
        .excludePathPatterns("/user/login").excludePathPatterns("/user/register");
    }

    //视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/march").setViewName("user/list");
    }
}
