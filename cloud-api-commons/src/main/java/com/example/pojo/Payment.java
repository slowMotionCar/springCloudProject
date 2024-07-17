package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Payment
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/15 11:50
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}