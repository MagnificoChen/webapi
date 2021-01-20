package com.classdesign.webapi.jsonobject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 */
public class StudentDataJson {
        private ArrayList<LeaderJson> stulist;

    public ArrayList<LeaderJson> getStulist() {
        return stulist;
    }

    public void setStulist(ArrayList<LeaderJson> stulist) {
        this.stulist = stulist;
    }
}
