package com.pdsu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author wl
 * @Date 2021/6/4 17:45
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_ok , id: " + id + "\t" + "😄哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "30000")})
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_Out,id"
                + id + "\t" + "😄哈哈" + "耗时:" + timeNumber + "秒钟";
    }

    public String paymentInfo_TimeOutHandle(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_TimeOutHandle,id"
                + id + "\t" + "系统繁忙或运行异常" + "(～﹃～)~zZ";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enable",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号:" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数,请稍后再试. id" + id;
    }
}
