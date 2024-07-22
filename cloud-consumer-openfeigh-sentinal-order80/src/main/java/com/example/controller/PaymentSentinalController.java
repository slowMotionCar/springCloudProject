package com.example.controller;

import com.example.service.PaymentSentinalNacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentSentinalController
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/16 19:58
 * @Version: 1.0
 */


@RestController
public class PaymentSentinalController {
    @Resource
    PaymentSentinalNacosService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {

        return id.toString();
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {

        return id.toString();
    }
}
