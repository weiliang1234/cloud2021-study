package com.pdsu.springcloud.controller;

import com.pdsu.springcloud.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @Date 2021/6/4 20:20
 */
@Log4j2
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ok(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/out/{id}")
    public String paymentInfo_Out(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("result:" + result);
        return result;
    }
}
