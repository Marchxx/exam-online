package com.march;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ComponentScan("com.xiyou.common.config")
@MapperScan("com.march.dao")
@SpringBootApplication
public class ExamOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamOnlineApplication.class, args);
    }

}
