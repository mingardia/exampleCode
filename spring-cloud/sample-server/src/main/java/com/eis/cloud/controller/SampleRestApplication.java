package com.eis.cloud.controller;

import com.eis.cloud.service.TodoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by mingardia on 1/31/16.
 */

@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class SampleRestApplication {

    @Autowired
    private ApplicationContext ctx;


    private static final Log logger = LogFactory.getLog(SampleRestApplication.class);


    public static void main(String[] args) {

        SpringApplication.run(SampleRestApplication.class, args);
    }

    @Bean
    TodoService todoService()
    {
        return new TodoService();
    }


}
