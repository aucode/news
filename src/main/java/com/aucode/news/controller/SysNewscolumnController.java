package com.aucode.news.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.aucode.news.entity.SysAdmin;
import com.aucode.news.entity.SysNewscolumn;
import com.aucode.news.service.SysNewscolumnService;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
@Controller
@RequestMapping("/newscolumn")
public class SysNewscolumnController {

    @Autowired
    SysNewscolumnService newscolumnService;


    /**
     * 修改栏目
     * @param newscolumn
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/update")
    public String updateColumn(SysNewscolumn newscolumn, ModelMap model, HttpSession session){
        int i = newscolumn.getColumnid();
        model.put("msg",newscolumnService.updateColumn(session,newscolumn));

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        IPage<SysNewscolumn> pageColumnList = newscolumnService.getPageColumnList(1, 5);
        model.put("columnpage",pageColumnList);

        long c = 0;
        if(pageColumnList.getTotal() % pageColumnList.getSize() !=0){
            c = pageColumnList.getTotal() / pageColumnList.getSize() + 1;
        }else{
            c =pageColumnList.getTotal() / pageColumnList.getSize();
        }
        model.put("page",c);
        return "admin/admin_type";
    }


    /**
     * 删除栏目
     * @param
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/column.del")
    public String delColumn(SysNewscolumn newscolumn, ModelMap model, HttpSession session){

        int i = newscolumn.getColumnid();
        model.put("msg",newscolumnService.delColumn(session,i));

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        IPage<SysNewscolumn> pageColumnList = newscolumnService.getPageColumnList(1, 5);
        model.put("columnpage",pageColumnList);

        long c = 0;
        if(pageColumnList.getTotal() % pageColumnList.getSize() !=0){
            c = pageColumnList.getTotal() / pageColumnList.getSize() + 1;
        }else{
            c =pageColumnList.getTotal() / pageColumnList.getSize();
        }
        model.put("page",c);
        return "admin/admin_type";
    }


    /**
     * 添加新闻栏目
     * @param model
     * @param session
     * @param newscolumn
     * @return
     */
    @PostMapping("/add")
    public String addColumn(ModelMap model, HttpSession session, SysNewscolumn newscolumn){

        /*
            1、添加数据
            2、读取数据：
                        （1）分页查看全部栏目数据。
                        （2）登录名
                        （3）登录权限级别

         */
        model.put("msg",newscolumnService.addColumn(session,newscolumn));
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        IPage<SysNewscolumn> pageColumnList = newscolumnService.getPageColumnList(1, 5);
        model.put("columnpage",pageColumnList);

        long c = 0;
        if(pageColumnList.getTotal() % pageColumnList.getSize() !=0){
            c = pageColumnList.getTotal() / pageColumnList.getSize() + 1;
        }else{
            c =pageColumnList.getTotal() / pageColumnList.getSize();
        }
        model.put("page",c);
        return "admin/admin_type";
    }

}

