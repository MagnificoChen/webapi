package com.classdesign.webapi.jsonobject;

/**
 * @author ysj
 */
public class LoginJson {

    private int code;
    private String msg;
    private DataJson data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }
}
