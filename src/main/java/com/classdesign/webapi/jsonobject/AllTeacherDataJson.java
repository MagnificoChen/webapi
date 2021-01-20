package com.classdesign.webapi.jsonobject;

import java.util.ArrayList;

/**
 * @author ysj
 */
public class AllTeacherDataJson {
    ArrayList<OneAllTeacherJson> userlist;

    public ArrayList<OneAllTeacherJson> getUserlist() {
        return userlist;
    }

    public void setUserlist(ArrayList<OneAllTeacherJson> userlist) {
        this.userlist = userlist;
    }
}
