package com.pdsu.springcloud.controller;

import com.pdsu.springcloud.entities.CommonResult;
import com.pdsu.springcloud.entities.Payment;
import com.pdsu.springcloud.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author wl
 * @Date 2021/5/30 10:56
 */
@RestController
@Log4j2
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        }else {
            return new CommonResult(500, "插入失败");
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：" + payment);

        if (payment == null) {
            return new CommonResult(404, "查询失败");
        }else {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }
    }
}
