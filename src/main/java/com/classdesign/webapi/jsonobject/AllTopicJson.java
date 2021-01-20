package com.classdesign.webapi.jsonobject;

/**
 * @author ysj
 */
public class AllTopicJson {
    private int code;
    private String msg;
    private AllTopicDataJson data;

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

    public AllTopicDataJson getData() {
        return data;
    }

    public void setData(AllTopicDataJson data) {
        this.data = data;
    }
}
