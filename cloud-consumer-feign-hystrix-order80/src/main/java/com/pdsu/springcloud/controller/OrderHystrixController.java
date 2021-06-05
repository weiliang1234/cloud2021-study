package com.pdsu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pdsu.springcloud.service.PaymentHystrixService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
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
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //默认的服务降级
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
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "50000")})
    public String paymentInfo_Out(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Out(id);
        log.info("result:" + result);
        return result;
    }

    public String paymentInfo_TimeOutHandle(Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试";
    }

    // NoSQL
    // 为啥 redis 适合做缓存？只是因为他对缓存有特定优化吗？ 乐观锁+对缓存有优化(Key -> value)
    // es 为啥适合做搜索？乐观锁+倒排索引(Crul RestFul)
    // mgdb 大文本数据缓存？乐观锁

    // 关系型SQL
    // mysql --> 行悲观锁

    /**
     * startA
     * String id = 1;
     * String name = 111;
     * double version = 1.0;
     * thread1 ---> startA -> startA.name = 2222, startA.version++ ---> startA = endA;
     * startA
     * id = 1
     * name = 2222
     * version = 2.0
     * thread2 ---> startA -> startA.id = 2 --->
     * startA = endA (endA.version == startA.version ? startA = endA --> startA.version++ : try loop);
     *
     */

    // MQ, 负载均衡, 服务降级, 线程
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后再试";
    }
}


/*
class B {

    */
/**
     * 测试
     * @param args
     *//*

    public static void main(String[] args) {
        int[] num = {3, 45,78,64,52,11,64,55,99,11,18};
        // 3 45 18 64 52 11 64 55 99 11 78
        // 3 45 18 11 52 11 64 55 99 64 78
        // 3 45 18 11 11 52 64 55 99 64 78
        // 3 11 18 11 45 52 64 55 99 64 78
        // 11 18 11  || 45 52 64 55 99 64 78
        // 3 11 11 18 45 || 52 64 55 99 64 78
        // 3 11 11 18 45 52 || 64 55 99 64 78
        // 3 11 11 18 45 52 || 55 64 99 64 78
        // 3 11 11 18 45 52 55 || 64 99 64 78
        // 3 11 11 18 45 52 55 64 || 99 64 78
        // 3 11 11 18 45 52 55 64 || 78 64 99
        // 3 11 11 18 45 52 55 64 64 || 78 99
        // 3 11 11 18 45 52 55 64 64 78 || 99
        // 3 11 11 18 45 52 55 64 64 78 99
        QuickSort(num,0,num.length-1);
        for (int i : num) {
            System.out.print(i + " ");
        }
    }
    */
/**
     * 快速排序
     * @param num	排序的数组
     * @param left	数组的前针
     * @param right 数组后针
     *//*

    private static void QuickSort(int[] num, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = num[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (num[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (num[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }
}*/
