package com.classdesign.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.classdesign.webapi.dao.*;
import com.classdesign.webapi.jsonobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 */
@RestController

public class TopicController {
    @Autowired
    TopicRepo topicRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StudentRepo studentRepo;

    @RequestMapping("/api/topic/get-by-teacher")
    public String getByTeacher(@RequestParam("uid") int uid){
        User user = userRepo.findById(uid);

        TopicJson topicJson = new TopicJson();
        if(user==null){
            topicJson.setMsg("该用户id不存在");
            topicJson.setCode(-1);
        }else {
            List<Topic> list = topicRepo.findAllByTeacher(user.getUsername());

            if (list.size() == 0) {
                topicJson.setCode(-1);
                topicJson.setMsg("获取失败或无课题");
            } else {
                topicJson.setData(new TopicDataJson());
                topicJson.getData().setTopiclist(new ArrayList<>());
                int todo = 0, done = 0;
                for (Topic topic : list) {
                    //计数完成和未完成的课题
                    if (topic.getIsdone()!=null&&topic.getIsdone().equals("Y")) {
                        done++;
                    } else {
                        todo++;
                    }
                    topicJson.getData().getTopiclist().add(new OneTopicJson(topic,""+uid));

                }
                topicJson.getData().setDone(done);
                topicJson.getData().setTodo(todo);

            }
        }
        return JSON.toJSONString(topicJson);
    }

    @RequestMapping("/api/topic/delete")
    public String delete(@RequestParam("tid") String tid){
        ResultJson ResultJson = new ResultJson();
        if(topicRepo.findById(Integer.parseInt(tid))==null){
            ResultJson.setMsg("该课题不存在");
            ResultJson.setCode(-1);
        }else{
            topicRepo.deleteById(Integer.parseInt(tid));
            ResultJson.setCode(0);
            ResultJson.setMsg("删除成功");
        }
        return JSON.toJSONString(ResultJson);
    }

    @RequestMapping("/api/topic/get-all")
    public String getAll(){
        List<Topic> topicList = topicRepo.findAll();
        AllTopicJson allTopicJson = new AllTopicJson();
        allTopicJson.setMsg("查询成功");
        allTopicJson.setCode(0);
        allTopicJson.setData(new AllTopicDataJson());

        ArrayList<OneAllTopicJson> oneAllTopicJsons = new ArrayList<>();
        for(Topic topic:topicList){
            User teacher = userRepo.findByUsername(topic.getTeacher());
            oneAllTopicJsons.add(new OneAllTopicJson(topic,teacher));
        }
        allTopicJson.getData().setTopiclist(oneAllTopicJsons);

        return JSON.toJSONString(allTopicJson);
    }



    @RequestMapping("/topic/add-taskbook")
    public String addTaskBook(MultipartFile uploadFile) throws IOException {
        InputStream filesource = uploadFile.getInputStream();


        Student student = studentRepo.findById(3);
        String name = "D:/upload/"+Integer.parseInt(student.getName())+".docx";
        File file = new File(name);
        BufferedInputStream bis = new BufferedInputStream(filesource);
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buff = new byte[1024];
        while(bis.read(buff)!= -1)
        {
            outputStream.write(buff,0,buff.length);
            outputStream.flush();
        }
        outputStream.close();
        filesource.close();//关闭输入输出流

        String url = student.getName();
        student.setName(""+(Integer.parseInt(student.getName())+1));
        studentRepo.save(student);

        TaskBookJson taskBookJson = new TaskBookJson();
        taskBookJson.setMsg("上传成功");
        taskBookJson.setCode(0);
        taskBookJson.setData(new Url());
        taskBookJson.getData().setUrl(url);
        return JSON.toJSONString(taskBookJson);
    }
    @RequestMapping("/topic/download-report/{url}")
    public String downloadReport(HttpServletResponse response, @PathVariable("url") String url){

        ResultJson resultJson = new ResultJson();
        String fileName = url + ".docx";

        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();

            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File("D:/upload/" + fileName)));
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    resultJson.setMsg("下载失败");
                    resultJson.setCode(-1);
                    return JSON.toJSONString(resultJson);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    resultJson.setMsg("下载失败");
                    resultJson.setCode(-1);
                    return JSON.toJSONString(resultJson);
                }
            }
        }
        resultJson.setCode(0);
        resultJson.setMsg("下载成功");
        return JSON.toJSONString(resultJson);
    }

    @RequestMapping("/api/topic/add")
    public String add(@RequestParam("topic") String name,@RequestParam("course") String course,@RequestParam("mession") String mession,@RequestParam(value = "url",required = false) String url,@RequestParam("uid") String uid,
                      @RequestParam("direction") String[] direction,@RequestParam("grade") String[] grade){
        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setName(name);
        topic.setTask(mession);
        topic.setUrl(url==null?"":url);
        topic.setTeacher(userRepo.findById(Integer.parseInt(uid)).getUsername());
        StringBuilder dir = new StringBuilder();
        StringBuilder gra = new StringBuilder();

        //将传来的数组解析为数据库要求的字符串格式
        for(String str:direction){
            dir.append(str+",");
        }
        dir.deleteCharAt(dir.length()-1);
        for(String str:grade){
            gra.append(str+",");
        }
        gra.deleteCharAt(gra.length()-1);
        topic.setDirection(dir.toString());
        topic.setGrade(gra.toString());
        topicRepo.save(topic);
        ResultJson resultJson = new ResultJson();
        resultJson.setMsg("上传成功");
        resultJson.setCode(0);
        return JSON.toJSONString(resultJson);

    }
}
