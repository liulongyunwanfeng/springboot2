package com.django.springboot2.web;

/**
 * @author liulongyun
 * @create 2019/5/29 14:44
 **/
public class HttpResult {

    private int status;
    private String msg;
    private Object Data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
