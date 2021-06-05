package com.pdsu.springcloud.controller;

import com.pdsu.springcloud.service.PaymentHystrixService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @Date 2021/6/4 21:28
 */
@Log4j2
@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/out/{id}")
    public String paymentInfo_Out(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Out(id);
        log.info("result:" + result);
        return result;
    }

}
