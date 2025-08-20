package com.wzy.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.beans.ExceptionListener;

/**
 * ClassName: OjSystemApplication
 * Description
 *
 * @Author wzy
 * @Create 2025/7/30 19:03
 * @Version 1.0
 */

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class OjGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OjGatewayApplication.class,args);
    }
}
