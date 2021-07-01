package com.aucode.news.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.aucode.news.entity.User;
import com.aucode.news.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-09
 */
@Controller
@RequestMapping("/user")
public class SySUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String testData(ModelMap model){
        model.put("test",userService.getAllUserInfo());
        return "index";
    }

    @PostMapping("/login")
    public String login(ModelMap model, User user, HttpSession session){

        if (!userService.verificationLoginInfo(user,session)){

            model.put("message","用户名或密码有误！");
        }else{

            model.put("message","登陆成功！");
        }

        return "index";
    }

}

