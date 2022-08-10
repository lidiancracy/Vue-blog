package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName sgblogApp
 * @Description TODO
 * @Date 2022/8/9 13:57
 */
@SpringBootApplication
@MapperScan("com.sangeng.mapper")
public class sgblogApp {
    public static void main(String[] args) {
        SpringApplication.run(sgblogApp.class,args);
    }
}
