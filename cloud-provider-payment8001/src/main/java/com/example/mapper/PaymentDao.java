package com.example.mapper;

import com.example.pojo.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PaymentDao
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/15 11:52
 * @Version: 1.0
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}