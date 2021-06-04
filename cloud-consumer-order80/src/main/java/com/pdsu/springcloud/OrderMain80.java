package com.pdsu.springcloud;

import com.pdsu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author wl
 * @Date 2021/5/30 15:41
 */
@SpringBootApplication
@EnableEurekaClient
//配置负载均衡,在访问CLOUD-PAYMENT-SERVICE服务时使用指定的MySelfRule.class规则
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
