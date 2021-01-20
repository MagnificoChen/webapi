package com.classdesign.webapi.jsonobject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 */
public class LeaderJson {
    private String name;
    private String stuId;
    private String direction;
    private int score;
    private String url;
    ArrayList<MemberJson> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<MemberJson> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<MemberJson> children) {
        this.children = children;
    }
}
