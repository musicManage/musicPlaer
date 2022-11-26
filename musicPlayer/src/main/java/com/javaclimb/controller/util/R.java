package com.javaclimb.controller.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R {
    //操作是否成功
    private Boolean flag;

    // 操作返回的数据
    private Object data;

    //状态码
    private int code;

    //返回信息
    private String msg;

    public R(Boolean flag){
        this.flag=flag;
    }

    public static R success(String msg){
        R r = new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setFlag(true);
        r.setData(null);

        return r;
    }

    public static R success(String msg,Object data){
        R r = success(msg);
        r.setData(data);

        return r;
    }

    public static R error(String msg){
        R r = success(msg);
        r.setFlag(false);
        r.setCode(0);

        return r;
    }

    public static R error(String msg,Object data){
        R r = success(msg);
        r.setFlag(false);
        r.setData(data);
        r.setCode(0);

        return r;
    }
}
