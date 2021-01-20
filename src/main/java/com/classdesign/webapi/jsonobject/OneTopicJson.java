package com.classdesign.webapi.jsonobject;

import com.classdesign.webapi.dao.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ysj
 */
public class OneTopicJson {
    private String tid;
    private String topic;
    private String course;
    private List<String> direction;
    private List<String> grade;
    private String mession;
    //1完成，0未完成
    private int status;
    private String uid;

    OneTopicJson(){}
    public OneTopicJson(Topic topic,String uid){
        List<String> direction = Arrays.asList(topic.getDirection().split(","));
        List<String> grade = Arrays.asList(topic.getGrade().split(","));

        this.setTid(""+topic.getId());
        this.setCourse(topic.getCourse());
        this.setDirection(direction);
        this.setGrade(grade);
        this.setMession(topic.getTask());
        this.setTopic(topic.getName());
        this.setStatus(topic.getIsdone()!=null&&topic.getIsdone().equals("Y")?1:0);
        this.setUid(uid);
    }
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<String> getDirection() {
        return direction;
    }

    public void setDirection(List<String> direction) {
        this.direction = direction;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public String getMession() {
        return mession;
    }

    public void setMession(String mession) {
        this.mession = mession;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
