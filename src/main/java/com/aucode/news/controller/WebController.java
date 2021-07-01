package com.aucode.news.controller;

import com.aucode.news.entity.SysNewclass;
import com.aucode.news.entity.SysNews;
import com.aucode.news.entity.SysNewsColumnClass;
import com.aucode.news.service.SysNewclassService;
import com.aucode.news.service.SysNewsService;
import com.aucode.news.service.SysNewscolumnService;
import com.aucode.news.util.Utils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

/**
 * @Project news
 * @Description: Web
 * @Author
 * @Explain
 * @Date 2021-06-19 13:19
 */
@Controller
public class WebController {

    @Autowired
    SysNewscolumnService newscolumnService;

    @Autowired
    SysNewclassService newclassService;

    @Autowired
    SysNewsService newsService;

    /**
     * 浏览文章
     * @return
     */
    @GetMapping("/browse")
    public String toBrowse(ModelMap model, int id){


        model.put("columnType",newscolumnService.getType());
        /**
         * 文章详情
         */
        model.put("news",newsService.getBrowse(id));
        return "browse";
    }

    /**
     * 首页搜索
     * @return
     */
    @GetMapping("/index.search")
    public String indexSearch(ModelMap model, String search){
        model.put("columnclass",search);
        /**
         * 1、所有栏目类型
         * 2、文章分页5条数据
         */
        model.put("columnType",newscolumnService.getType());
        //读取前十数据
        model.put("newstop","");
        IPage<SysNews> pageNewsList = newsService.getSearchNewsList(search);

        List<SysNews> records = pageNewsList.getRecords();
        for (int i = 0; i < pageNewsList.getRecords().size(); i++) {
            Set<String> imgStr = Utils.getImgStr(records.get(i).getContent());
            if (imgStr.size() > 0){
                records.get(i).setTop(imgStr.toArray()[0].toString());
            }else {
                records.get(i).setTop("imgStr.toArray()[0].toString()");
            }


        }
        pageNewsList.setRecords(Utils.removeHtml(pageNewsList.getRecords()));
        //文章分页
        model.put("newslist",pageNewsList);
        model.put("more",pageNewsList.getSize() * pageNewsList.getCurrent() < pageNewsList.getTotal());
        model.put("pageMun",0);
        return "index";
    }

    /**
     * 首页类型
     * @return
     */
    @GetMapping("/type")
    public String type(ModelMap model, SysNewclass sysNewclass){
        model.put("columnclass",sysNewclass.getContent());
        /**
         * 1、所有栏目类型
         * 2、文章分页5条数据
         */
        model.put("columnType",newscolumnService.getType());
        model.put("newstop","");
        IPage<SysNews> pageNewsList = newsService.getNewsList(sysNewclass);

        List<SysNews> records = pageNewsList.getRecords();
        for (int i = 0; i < pageNewsList.getRecords().size(); i++) {
            Set<String> imgStr = Utils.getImgStr(records.get(i).getContent());
            if (imgStr.size() > 0){
                records.get(i).setTop(imgStr.toArray()[0].toString());
            }else {
                records.get(i).setTop("imgStr.toArray()[0].toString()");
            }


        }
        pageNewsList.setRecords(Utils.removeHtml(pageNewsList.getRecords()));
        //文章分页
        model.put("newslist",pageNewsList);
        model.put("more",pageNewsList.getSize() * pageNewsList.getCurrent() < pageNewsList.getTotal());
        model.put("pageMun",0);
        return "index";
    }

    /**
     * 首页分页
     * @return
     */
    @GetMapping("/page")
    public String pageIndex(ModelMap model, int pageNum){
        model.put("columnclass","新闻");
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

        /**
         * 1、所有栏目类型
         * 2、文章分页5条数据
         */
        model.put("columnType",newscolumnService.getType());

        IPage<SysNews> pageNewsList = newsService.getPageNewsList(1, pageNum * 5);

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
        model.put("pageMun",++pageNum);
        return "index";
    }
}
