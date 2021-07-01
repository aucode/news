package com.aucode.news.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.aucode.news.entity.SysAdmin;
import com.aucode.news.service.SysAdminService;
import com.aucode.news.service.SysNewsService;
import com.aucode.news.util.MD5;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-10
 */
@Controller
@RequestMapping("/admin")
public class SysAdminController {

    @Autowired
    private SysAdminService adminService;
    @Autowired
    private SysNewsService newsService;


    /**
     * 修改密码
     * @return
     */
    @PostMapping("/admin.updatepwd")
    public String upadtePwd(SysAdmin admin,ModelMap model,HttpSession session){
        model.put("msg",adminService.upadtePwd(admin));
        /*
            1.读取用户登录信息
            2.分页读取用户列表
         */
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        if (loginSession.getAuthority().equals("0")) {
            return "redirect:/admin.toindex";
        }
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());
        IPage<SysAdmin> pageUserList = adminService.getPageColumnList(1, 10);


        model.put("userpage",pageUserList);
        long c = 0;
        if(pageUserList.getTotal() % pageUserList.getSize() !=0){
            c = pageUserList.getTotal() / pageUserList.getSize() + 1;
        }else{
            c =pageUserList.getTotal() / pageUserList.getSize();
        }
        model.put("page",c);
        return "admin/admin_user";
    }

    @GetMapping("/adminuser.page")
    public String adminUserPage(HttpSession session, Integer pageNum,ModelMap model){

        /*
            1.读取用户登录信息
            2.分页读取用户列表
         */
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        if (loginSession.getAuthority().equals("0")) {
            return "redirect:/admin.toindex";
        }
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());
        IPage<SysAdmin> pageUserList = adminService.getPageColumnList(pageNum, 10);


        model.put("userpage",pageUserList);
        long c = 0;
        if(pageUserList.getTotal() % pageUserList.getSize() !=0){
            c = pageUserList.getTotal() / pageUserList.getSize() + 1;
        }else{
            c =pageUserList.getTotal() / pageUserList.getSize();
        }
        model.put("page",c);
        return "admin/admin_user";
    }

    /**
     * 添加
     * @param session
     * @param admin
     * @param model
     * @return
     */
    @PostMapping("/add")
    public String addAdmin(HttpSession session, SysAdmin admin,ModelMap model){

        //推荐账户
        model.put("msg",adminService.add(admin));
        /*
            1.读取用户登录信息
            2.分页读取用户列表
         */
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        if (loginSession.getAuthority().equals("0")) {
            return "redirect:/admin.toindex";
        }
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());
        IPage<SysAdmin> pageUserList = adminService.getPageColumnList(1, 10);


        model.put("userpage",pageUserList);
        long c = 0;
        if(pageUserList.getTotal() % pageUserList.getSize() !=0){
            c = pageUserList.getTotal() / pageUserList.getSize() + 1;
        }else{
            c =pageUserList.getTotal() / pageUserList.getSize();
        }
        model.put("page",c);
        return "admin/admin_user";
    }

    /**
     * 后台管理员登录
     * @param session
     * @param admin
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String adminLogin(HttpSession session, SysAdmin admin,ModelMap model){

        //加密密码
        admin.setAdminPassword(MD5.md5Str(admin.getAdminPassword()));
        int loginInfo = adminService.verificationAdminLoginInfo(session, admin);
        if (loginInfo == 1){
            SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
            model.put("user",loginSession.getAdminName());
            model.put("level",loginSession.getAuthority());
            //读取前十数据
            model.put("newstop",newsService.getTopNewsList());
            return "redirect:/admin.toindex";
        }else if (loginInfo == 0){

            model.put("message","用户名或密码错误！");
            return "admin/login";
        }

        model.put("message","账户被冻结请联系管理员！");
        return "admin/login";
    }

    @GetMapping("/sign.out")
    public String signOut(HttpSession session){
        SessionUtil.outAdminLoginSession(session);
        return "redirect:/admin/login";
    }

}

