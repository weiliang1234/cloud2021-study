package com.pdsu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author wl
 * @Date 2021/6/5 16:11
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Out(Integer id) {
        return "PaymentFallbackService fall back paymentInfo_Out";
    }
}
