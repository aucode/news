package com.aucode.news.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.aucode.news.entity.SysAdmin;
import com.aucode.news.entity.SysNewclass;
import com.aucode.news.entity.SysNewscolumn;
import com.aucode.news.service.SysNewclassService;
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
@RequestMapping("/newclass")
public class SysNewclassController {
    @Autowired
    SysNewclassService newclassService;

    /**
     * 获取栏目下的类型
     * @param model
     * @param session
     * @param key
     * @return
     */
    @GetMapping("/columnclass")
    public String cloumnClass(ModelMap model, HttpSession session, String key){


        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        Page<SysNewclass> sysNewclassPage = newclassService.getcloumnClassByKey(key, 1, 5);
        model.put("key",key);
        model.put("classPage",sysNewclassPage);
        long c = 0;
        if(sysNewclassPage.getTotal() % sysNewclassPage.getSize() !=0){
            c = sysNewclassPage.getTotal() / sysNewclassPage.getSize() + 1;
        }else{
            c =sysNewclassPage.getTotal() / sysNewclassPage.getSize();
        }
        model.put("page",c);
        return "admin/admin_class";
    }


    /**
     * 添加栏目类型
     * @param model
     * @param session
     * @param newclass
     * @return
     */
    @PostMapping("clomunclass.add")
    public String add(ModelMap model, HttpSession session,SysNewclass newclass){

        //添加
        model.put("msg",newclassService.addCloumnClass(newclass));
        model.put("key",newclass.getColumnid());

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        String munber = newclass.getColumnid().toString();
        Page<SysNewclass> sysNewclassPage = newclassService.getcloumnClassByKey(munber, 1, 5);
        model.put("classPage",sysNewclassPage);
        long c = 0;
        if(sysNewclassPage.getTotal() % sysNewclassPage.getSize() !=0){
            c = sysNewclassPage.getTotal() / sysNewclassPage.getSize() + 1;
        }else{
            c =sysNewclassPage.getTotal() / sysNewclassPage.getSize();
        }
        model.put("page",c);
        return "admin/admin_class";
    }

    /**
     * 修改
     * @param model
     * @param session
     * @param newclass
     * @return
     */
    @PostMapping("/update")
    public String updateClass(ModelMap model,HttpSession session,SysNewclass newclass){

        model.put("msg",newclassService.updateClass(newclass));


        model.put("key",newclass.getColumnid());

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        String munber = newclass.getColumnid().toString();
        Page<SysNewclass> sysNewclassPage = newclassService.getcloumnClassByKey(munber, 1, 5);
        model.put("classPage",sysNewclassPage);
        long c = 0;
        if(sysNewclassPage.getTotal() % sysNewclassPage.getSize() !=0){
            c = sysNewclassPage.getTotal() / sysNewclassPage.getSize() + 1;
        }else{
            c =sysNewclassPage.getTotal() / sysNewclassPage.getSize();
        }
        model.put("page",c);
        return "admin/admin_class";
    }

    /**、
     * 类型删除
     * @param model
     * @param session
     * @param newclass
     * @return
     */
    @PostMapping("cloumnclass.del")
    public String del(ModelMap model,HttpSession session,SysNewclass newclass){

        /*
            1.下级是否有数据
            2.删除
         */

        model.put("msg",newclassService.columnCalssdel(newclass));

        model.put("key",newclass.getColumnid());

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        String munber = newclass.getColumnid().toString();
        Page<SysNewclass> sysNewclassPage = newclassService.getcloumnClassByKey(munber, 1, 5);
        model.put("classPage",sysNewclassPage);
        long c = 0;
        if(sysNewclassPage.getTotal() % sysNewclassPage.getSize() !=0){
            c = sysNewclassPage.getTotal() / sysNewclassPage.getSize() + 1;
        }else{
            c =sysNewclassPage.getTotal() / sysNewclassPage.getSize();
        }
        model.put("page",c);
        return "admin/admin_class";
    }

}

