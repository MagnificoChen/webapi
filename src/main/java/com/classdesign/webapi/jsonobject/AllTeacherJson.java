package com.classdesign.webapi.jsonobject;

/**
 * @author ysj
 */
public class AllTeacherJson extends ResultJson {
    AllTeacherDataJson data;

    public AllTeacherDataJson getData() {
        return data;
    }

    public void setData(AllTeacherDataJson data) {
        this.data = data;
    }
}
