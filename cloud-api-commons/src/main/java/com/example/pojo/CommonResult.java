package com.example.pojo;

/**
 * @ClassName: CommonResult
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/15 11:51
 * @Version: 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @descrition：创建一个只有两个参数的构造方法
 * @auther: zzl
 * @date: 2022/5/8 18:11
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;


    public CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResult(Integer code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

