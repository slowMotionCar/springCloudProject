package com.example.service.impl;

import com.example.mapper.PaymentDao;
import com.example.pojo.Payment;
import com.example.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: PaymentServiceImpl
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/15 11:56
 * @Version: 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
