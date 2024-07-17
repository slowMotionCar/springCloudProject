package com.example.service;

import com.example.pojo.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PaymentService
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/15 11:55
 * @Version: 1.0
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}