package cn.zwq.springboot_validation.model;

import lombok.Data;

@Data
public class MyResponse<T> {
    private Integer statusCode;
    private T data;
}