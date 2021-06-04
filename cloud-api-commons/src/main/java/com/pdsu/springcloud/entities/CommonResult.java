package com.pdsu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl
 * @Date 2021/5/30 15:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    //返回数据可能为null,所以调用自己的全参构造器
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
