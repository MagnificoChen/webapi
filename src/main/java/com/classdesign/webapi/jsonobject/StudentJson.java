package com.classdesign.webapi.jsonobject;

/**
 * @author ysj
 */
public class StudentJson {
    private int code;
    private String msg;
    StudentDataJson data;

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

    public StudentDataJson getData() {
        return data;
    }

    public void setData(StudentDataJson data) {
        this.data = data;
    }
}
