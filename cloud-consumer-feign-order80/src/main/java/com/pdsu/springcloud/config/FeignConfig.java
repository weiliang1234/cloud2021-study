package com.pdsu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wl
 * @Date 2021/6/4 16:30
 */
@Configuration
public class FeignConfig {

    //openFeign打印日志
    @Bean
    Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }
}
