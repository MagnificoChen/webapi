package com.classdesign.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.classdesign.webapi.dao.User;
import com.classdesign.webapi.dao.UserRepo;
import com.classdesign.webapi.jsonobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HttpServletRequest request;



    @RequestMapping("/login")
    public String login(@RequestParam(name = "username", required = false) String username, @RequestParam(name = "password", required = false) String password){
        LoginJson loginJson = new LoginJson();
        if(username == null || password == null || username.equals("")  || password.equals(""))
        {
            loginJson.setCode(-1);
            loginJson.setMsg("账号或密码为空");

        }else {
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            User user = userRepo.findByUsername(username);
            if (user != null && user.getPassword().equals(md5Password)) {
                loginJson.setData(new DataJson());
                loginJson.setCode(0);
                loginJson.setMsg("登陆成功");
                loginJson.getData().setName(username);
                loginJson.getData().setUid(""+user.getId());
                loginJson.getData().setRole(user.getIsadmin()!=null&&user.getIsadmin().equals("Y")?true:false);
            }else{
                loginJson.setCode(-1);
                loginJson.setMsg("用户不存在或密码错误");
            }

        }
//        loginJson.setData(new DataJson());
//        loginJson.setCode(0);
//        loginJson.setMsg("登陆成功");
//        loginJson.getData().setName("姚尚杰");
//        loginJson.getData().setUid(""+1);
//        loginJson.getData().setRole(true);
        return JSON.toJSONString(loginJson);

    }
    @RequestMapping("/user/get-all")
    public String getAll(){
        List<User> users = userRepo.findAll();
        AllTeacherJson allTeacherJson = new AllTeacherJson();
        allTeacherJson.setMsg("查询成功");
        allTeacherJson.setCode(0);
        allTeacherJson.setData(new AllTeacherDataJson());
        ArrayList<OneAllTeacherJson> oneAllTeacherJsons = new ArrayList<>();
        for(User user:users){
            oneAllTeacherJsons.add(new OneAllTeacherJson(user));
        }
        allTeacherJson.getData().setUserlist(oneAllTeacherJsons);

        return JSON.toJSONString(allTeacherJson);
    }

    @RequestMapping("/user/alter")
    public String alter(@RequestParam("uid")String uid,@RequestParam("name") String name,@RequestParam("tel") String tel,@RequestParam("mail") String mail,@RequestParam("role") boolean role,@RequestParam("password") String password){
        User user = new User();
        user.setId(Integer.parseInt(uid));
        user.setEmail(mail);
        user.setIsadmin(role?"Y":"N");
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setUsername(name);
        user.setPhone(tel);
        userRepo.save(user);
        ResultJson resultJson = new ResultJson();
        resultJson.setMsg("修改成功");
        resultJson.setCode(0);
        return JSON.toJSONString(resultJson);
    }

    @RequestMapping("/user/delete")
    public String delete(@RequestParam("uid") String uid){
        userRepo.deleteById(Integer.parseInt(uid));
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(0);
        resultJson.setMsg("删除成功");
        return JSON.toJSONString(resultJson);
    }
    @RequestMapping("/user/add")
    public String add(@RequestParam("name") String name,@RequestParam("tel") String tel,@RequestParam("mail") String mail,@RequestParam("role") boolean role,@RequestParam("password") String password){
        User user = new User();
        user.setEmail(mail);
        user.setIsadmin(role?"Y":"N");
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setUsername(name);
        user.setPhone(tel);
        userRepo.save(user);
        ResultJson resultJson = new ResultJson();
        resultJson.setCode(0);
        resultJson.setMsg("新增成功");
        return JSON.toJSONString(resultJson);
    }

}
