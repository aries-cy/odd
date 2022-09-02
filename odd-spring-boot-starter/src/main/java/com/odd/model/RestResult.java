package com.odd.model;

import lombok.Data;

@Data
public class RestResult<T> {

    private String code;

    private T content;

    private String msg;


    public RestResult(String code, String msg, T content) {
        this.code = code;
        this.content = content;
        this.msg = msg;
    }

    public RestResult(T content) {
        this.code = "00";
        this.content = content;
        this.msg = "操作成功";
    }
}
