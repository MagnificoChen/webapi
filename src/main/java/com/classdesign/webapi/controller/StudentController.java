package com.classdesign.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.classdesign.webapi.dao.Student;
import com.classdesign.webapi.dao.StudentRepo;
import com.classdesign.webapi.dao.TopicRepo;
import com.classdesign.webapi.jsonobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TopicRepo topicRepo;
    @RequestMapping("/get-by-topic")
    public String getByTopic(@RequestParam("tid") String tid){
        StudentJson studentJson = new StudentJson();
        if(topicRepo.findById(Integer.parseInt(tid))==null){
            studentJson.setCode(-1);
            studentJson.setMsg("该课题不存在");
        }else{
            studentJson.setMsg("查找成功");
            studentJson.setCode(0);

            List<Student> list = studentRepo.findAllByTid(tid);
            ArrayList<LeaderJson> leaderJsons= new ArrayList<LeaderJson>();

            LeaderJson leaderJson;
            MemberJson memberJson;


            for(Student student:list){
                if(student.getIsleader().equals("Y")){
                    leaderJson = new LeaderJson();
                    ArrayList<MemberJson> memberJsons = new ArrayList<>();
                    leaderJson.setName(student.getName());
                    leaderJson.setDirection(student.getDirection());
                    leaderJson.setStuId(student.getNumber());
                    leaderJson.setScore(Integer.parseInt(student.getScore()));
                    leaderJson.setUrl(student.getUrl()==null||student.getUrl().equals("")?"":student.getUrl());

                    for(Student member:list){
                        if(student.getTeam().equals(member.getTeam())&&!member.getIsleader().equals("Y")){
                            memberJson = new MemberJson();
                            memberJson.setName(member.getName());
                            memberJson.setDirection(member.getDirection());
                            memberJson.setStuId(member.getNumber());
                            memberJson.setScore(Integer.parseInt(member.getScore()));
                            memberJsons.add(memberJson);
                        }
                    }
                    leaderJson.setChildren(memberJsons);
                    leaderJsons.add(leaderJson);
                }
            }
            studentJson.setData(new StudentDataJson());
            studentJson.getData().setStulist(leaderJsons);
        }

        return JSON.toJSONString(studentJson, SerializerFeature.DisableCircularReferenceDetect);
    }


    @RequestMapping("/add-score")
    public String addScore(@RequestParam("student[name]") String[] name,@RequestParam("student[stuId]") String[] stuId,@RequestParam("student[score]") int[] score){
        String stuName,id;
        int stuScore;
        ResultJson resultJson = new ResultJson();
        for(int i=0;i<stuId.length;i++) {
            stuName = name[i];
            id = stuId[i];
            stuScore = score[i];
            Student student = studentRepo.findByNumber(id);


            if (student == null) {
                resultJson.setMsg("该学生不存在");
                resultJson.setCode(-1);
                break;
            } else if (!(stuScore <= 100 && stuScore >= 0)) {
                resultJson.setMsg("分数值不符合规范");
                resultJson.setCode(-1);
                break;
            } else {

                student.setScore("" + stuScore);
                studentRepo.save(student);
                resultJson.setMsg("打分成功");
                resultJson.setCode(0);
            }
        }
        return JSON.toJSONString(resultJson);
    }
}
