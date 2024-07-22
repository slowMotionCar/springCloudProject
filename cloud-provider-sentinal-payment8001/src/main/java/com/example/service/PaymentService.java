package com.example.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: PaymentService
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/16 16:36
 * @Version: 1.0
 */
@Service
public class PaymentService {
    /**
     * 正常访问，一切OK
     *
     * @param id
     * @return
     */
    @SentinelResource(value = "paymentInfo_OK", blockHandler  = "helloWorld",fallback = "helloWorld")
    public String paymentInfo_OK(Integer id) {
        int i = 1/0;
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     *
     * @param id
     * @return
     */
    @SentinelResource(value = "paymentInfo_TimeOut", fallback = "helloWorld", blockHandler  = "helloWorld")
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id: " + id + "\t" + "O(∩_∩)O，耗费3秒";
    }

    public String helloWorld(Integer id) {
        // 资源中的逻辑
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t" + "\t当前线程池名字" + Thread.currentThread().getName();
    }

    @SentinelResource(value = "mazda", fallback = "helloMazda", blockHandler = "helloMazda")
    public String mazda() {
        return "zoom! zoom!";
    }

    public String helloMazda() {
        // 资源中的逻辑
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t" + "\t当前线程池名字" + Thread.currentThread().getName();
    }
}