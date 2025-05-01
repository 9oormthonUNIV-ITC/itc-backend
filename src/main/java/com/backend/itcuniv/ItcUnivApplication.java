package com.backend.itcuniv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// 실행 후 기본 로그인 창 뜨지 않도록
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ItcUnivApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItcUnivApplication.class, args);
    }

}
