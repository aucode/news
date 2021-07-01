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
import com.aucode.news.entity.SysNews;
import com.aucode.news.service.SysNewclassService;
import com.aucode.news.service.SysNewsService;
import com.aucode.news.service.SysNewscolumnService;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-17
 */
@Controller
@RequestMapping("/news")
public class SysNewsController {


    @Autowired
    SysNewsService newsService;

    @Autowired
    SysNewscolumnService newscolumnService;

    @Autowired
    SysNewclassService newclassService;




    /**
     * 文章搜索页面渲染
     * @param model
     * @return
     */
    @PostMapping("/news.search")
    public String search(ModelMap model, HttpSession session,String search){
        //栏目数据
        model.put("column",newscolumnService.getAllColumnList());
        //类型数据
        model.put("class",newclassService.getAllColumnClassList());
        //搜索文章数据
        IPage<SysNews> pageColumnList = newsService.getSearchNewsList(search);
        model.put("newspage",pageColumnList);

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());


        model.put("page",-1);
        return "admin/admin_news";
    }


    /**
     * 修改状态
     * @param model
     * @param sysNews
     * @param session
     * @return
     */
    @PostMapping("update.topstate")
    public String updateTopState(ModelMap model, SysNews sysNews, HttpSession session){
        //修改状态
        model.put("msg",newsService.updateState(sysNews));
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());
        //读取前十数据
        model.put("newstop",newsService.getTopNewsList());
        return "admin/admin_index";
    }


    /**
     * 修改状态
     * @param model
     * @param sysNews
     * @param session
     * @return
     */
    @PostMapping("update.state")
    public String updateState(ModelMap model, SysNews sysNews, HttpSession session){
        //修改状态
        model.put("msg",newsService.updateState(sysNews));

        //栏目数据
        model.put("column",newscolumnService.getAllColumnList());
        //类型数据
        model.put("class",newclassService.getAllColumnClassList());
        //文章数据分页
        IPage<SysNews> pageColumnList = newsService.getPageNewsList(1, 5);
        model.put("newspage",pageColumnList);

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        long c = 0;
        if(pageColumnList.getTotal() % pageColumnList.getSize() !=0){
            c = pageColumnList.getTotal() / pageColumnList.getSize() + 1;
        }else{
            c =pageColumnList.getTotal() / pageColumnList.getSize();
        }
        model.put("page",c);
        return "admin/admin_news";
    }


    /**
     * 添加文章
     * @return
     */
    @PostMapping("addnews")
    public String addNews(ModelMap model, SysNews sysNews, HttpSession session){
        model.put("msg",newsService.add(sysNews));

        //栏目数据
        model.put("column",newscolumnService.getAllColumnList());
        //类型数据
        model.put("class",newclassService.getAllColumnClassList());
        //文章数据分页
        IPage<SysNews> pageColumnList = newsService.getPageNewsList(1, 5);
        model.put("newspage",pageColumnList);

        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        long c = 0;
        if(pageColumnList.getTotal() % pageColumnList.getSize() !=0){
            c = pageColumnList.getTotal() / pageColumnList.getSize() + 1;
        }else{
            c =pageColumnList.getTotal() / pageColumnList.getSize();
        }
        model.put("page",c);
        return "admin/admin_news";
    }



}

