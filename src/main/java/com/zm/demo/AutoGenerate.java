package com.zm.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Name: AutoGenerate
 * @Author: zhangming
 * @Date 2020/8/24 15:45
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.zm.demo.mapper")
public class AutoGenerate {

    public static void main(String[] args) {
        SpringApplication.run(AutoGenerate.class, args);
    }



}
