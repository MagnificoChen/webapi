package com.classdesign.webapi.jsonobject;

/**
 * @author ysj
 */
public class TopicJson {
    private int code;
    private String msg;
    private TopicDataJson data;

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

    public TopicDataJson getData() {
        return data;
    }

    public void setData(TopicDataJson data) {
        this.data = data;
    }
}
