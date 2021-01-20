package com.classdesign.webapi.jsonobject;

import com.classdesign.webapi.dao.User;

/**
 * @author ysj
 */
public class OneAllTeacherJson {

    private String uid;
    private String name;
    private String tel;
    private String mail;
    private boolean role;

    public OneAllTeacherJson(User user){
        setMail(user.getEmail());
        setName(user.getUsername());
        setRole(user.getIsadmin().equals("Y")?true:false);
        setTel(user.getPhone());
        setUid(""+user.getId());
    }
    public OneAllTeacherJson(){}
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

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
