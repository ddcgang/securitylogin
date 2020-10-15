package com.demo.securitylogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.demo.securitylogin.dao")
public class SecurityloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityloginApplication.class, args);
    }

}
