package com.pdsu.springcloud.service;

import com.pdsu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author wl
 * @Date 2021/5/30 10:46
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
