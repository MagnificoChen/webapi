package com.classdesign.webapi.jsonobject;

import com.classdesign.webapi.dao.Topic;
import com.classdesign.webapi.dao.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ysj
 */
public class OneAllTopicJson {
    private String tid;
    private String topic;
    private String course;
    private List<String> direction;
    private List<String> grade;
    private String mession;
    //1完成，0未完成
    private int status;
    private String name;
    private String tel;
    private String mail;
    private String uid;

    OneAllTopicJson(){}
    //根据topic构造
    public OneAllTopicJson(Topic topic){
        List<String> direction = Arrays.asList(topic.getDirection().split(","));
        List<String> grade = Arrays.asList(topic.getGrade().split(","));

        this.setTid(""+topic.getId());
        this.setCourse(topic.getCourse());
        this.setDirection(direction);
        this.setGrade(grade);
        this.setMession(topic.getTask());
        this.setTopic(topic.getName());
        this.setStatus(topic.getIsdone()!=null&&topic.getIsdone().equals("Y")?1:0);
    }
    public OneAllTopicJson(Topic topic, User teacher){
        this(topic);
        setMail(teacher.getEmail());
        setTel(teacher.getPhone());
        setName(teacher.getUsername());
        setUid(""+teacher.getId());
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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
