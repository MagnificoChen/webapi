package com.classdesign.webapi.jsonobject;

import java.util.ArrayList;

/**
 * @author ysj
 */
public class TopicDataJson {
    private int todo;
    private int done;
    private ArrayList<OneTopicJson> topiclist;

    public int getTodo() {
        return todo;
    }

    public void setTodo(int todo) {
        this.todo = todo;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public ArrayList<OneTopicJson> getTopiclist() {
        return topiclist;
    }

    public void setTopiclist(ArrayList<OneTopicJson> topiclist) {
        this.topiclist = topiclist;
    }
}
