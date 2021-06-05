package com.pdsu.springcloud.controller;

import com.pdsu.springcloud.entities.CommonResult;
import com.pdsu.springcloud.entities.Payment;
import com.pdsu.springcloud.service.PaymentFeignService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wl
 * @Date 2021/6/4 10:46
 */
@Log4j2
@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }
}
