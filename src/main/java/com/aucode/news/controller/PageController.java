package com.aucode.news.controller;

import com.aucode.news.entity.SysNewsColumnClass;
import com.aucode.news.util.Utils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import com.aucode.news.entity.SysAdmin;
import com.aucode.news.entity.SysNews;
import com.aucode.news.entity.SysNewscolumn;
import com.aucode.news.service.SysAdminService;
import com.aucode.news.service.SysNewclassService;
import com.aucode.news.service.SysNewsService;
import com.aucode.news.service.SysNewscolumnService;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @Project read
 * @Description: PageController
 * @Author
 * @Explain
 * @Date 2021-06-15 20:59
 */
@Controller
public class PageController {

    @Autowired
    SysNewscolumnService newscolumnService;

    @Autowired
    SysNewclassService newclassService;

    @Autowired
    SysNewsService newsService;

    @Autowired
    SysAdminService adminService;

    /**
     * 网站首页
     * @return
     */
    @GetMapping("/")
    public String Index(ModelMap model){
        model.put("columnclass","新闻");
        /**
         * 1、所有栏目类型
         * 2、文章分页5条数据
         */
        model.put("columnType",newscolumnService.getType());

        //读取前十数据
        model.put("newstop",newsService.getTopNewsList());

        List<SysNewsColumnClass> gethomelist = newscolumnService.gethomelist();
        for (int i = 0; i < gethomelist.size(); i++) {
            for(int j = 0;j < gethomelist.get(i).getListnews().size();j++){

                Set<String> imgStr = Utils.getImgStr(gethomelist.get(i).getListnews().get(j).getNewscontent());
                if (imgStr.size() > 0){
                    gethomelist.get(i).getListnews().get(j).setTop(imgStr.toArray()[0].toString());
                }else {
                    gethomelist.get(i).getListnews().get(j).setTop("http://localhost:8080/image/de.jpg");
                }
            }
        }


        model.put("typenews",gethomelist);

        IPage<SysNews> pageNewsList = newsService.getPageNewsList(1, 5);

        List<SysNews> records = pageNewsList.getRecords();
        for (int i = 0; i < pageNewsList.getRecords().size(); i++) {
            Set<String> imgStr = Utils.getImgStr(records.get(i).getContent());
            if (imgStr.size() > 0){
                records.get(i).setTop(imgStr.toArray()[0].toString());
            }else {
                records.get(i).setTop("http://localhost:8080/image/de.jpg");
            }

        }
        pageNewsList.setRecords(Utils.removeHtml(pageNewsList.getRecords()));
        //文章分页
        model.put("newslist",pageNewsList);

        model.put("more",pageNewsList.getSize() * pageNewsList.getCurrent() < pageNewsList.getTotal());
        model.put("pageMun",2);
        return "index";
    }




    /**
     * 跳转到后台登陆页面
     * @param model
     * @return
     */
    @GetMapping("/admin.tologin")
    public String toLogin(ModelMap model){
        return "admin/login";
    }


    /**
     * 文章管理页面渲染
     * @param model
     * @return
     */
    @GetMapping("/admin.tonews")
    public String toNews(ModelMap model, HttpSession session){
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
     * 跳转到后台首页
     * @param model
     * @return
     */
    @GetMapping("/admin.toindex")
    public String toIndex(ModelMap model, HttpSession session){
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());
        //读取前十数据
        model.put("newstop",newsService.getTopNewsList());
        return "admin/admin_index";
    }

    /**
     * 后台栏目页面渲染
     * @param model
     * @return
     */
    @GetMapping("/admin.column")
    public String toColumn(ModelMap model, HttpSession session){
        /*
            1.读取用户登录信息
            2.分页读取栏目列表
         */
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
     * 文章分页
     * @return
     */
    @GetMapping("news.page")
    public String newsPage(ModelMap model, int pageNum, HttpSession session){


        //栏目数据
        model.put("column",newscolumnService.getAllColumnList());
        //类型数据
        model.put("class",newclassService.getAllColumnClassList());
        //文章数据分页
        IPage<SysNews> pageColumnList = newsService.getPageNewsList(pageNum, 5);
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
     * 后台栏目页面分页查询
     * @param model
     * @return
     */
    @GetMapping("/column.page")
    public String pageColumn(int pageNum,ModelMap model, HttpSession session){
        /*
            1.读取用户登录信息
            2.分页读取栏目列表
         */
        SysAdmin loginSession = SessionUtil.getAdminLoginSession(session);
        model.put("user",loginSession.getAdminName());
        model.put("level",loginSession.getAuthority());

        IPage<SysNewscolumn> pageColumnList = newscolumnService.getPageColumnList(pageNum, 5);
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
     * 后台栏目页面渲染
     * @param model
     * @return
     */
    @GetMapping("/admin.type")
    public String toType(ModelMap model){
        return "admin/admin_type";
    }


    /**
     * 用户页面渲染
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/admin.user")
    public String toUser(ModelMap model, HttpSession session){
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
     * 后台管理员注册
     * @return
     */
    @PostMapping("/admin.reg")
    public String adminRegister(){
        return "reg";
    }
}
