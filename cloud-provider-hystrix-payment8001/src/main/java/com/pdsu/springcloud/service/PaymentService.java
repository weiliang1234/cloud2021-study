package com.pdsu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @Date 2021/6/4 17:45
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_ok , id: " + id + "\t" + "😄哈哈";
    }

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
}
