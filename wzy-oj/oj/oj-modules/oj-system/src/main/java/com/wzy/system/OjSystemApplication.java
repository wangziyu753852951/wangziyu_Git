package com.wzy.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: OjSystemApplication
 * Description
 *
 * @Author wzy
 * @Create 2025/7/30 19:03
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.wzy.**.mapper")
public class OjSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjSystemApplication.class,args);
    }
}
