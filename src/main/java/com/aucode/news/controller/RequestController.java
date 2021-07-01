package com.aucode.news.controller;

import com.aucode.news.entity.SysAdmin;
import com.aucode.news.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Project news
 * @Description: JsonController
 * @Author
 * @Explain
 * @Date 2021-06-24 18:00
 */
@RestController
public class RequestController {

    @Autowired
    private SysAdminService adminService;

    /**
     * 修改状态
     * @return
     */
    @PostMapping("/admin.update")
    public String upadte(int state,int id){
        adminService.updateByAdminId(state,id);
        return adminService.updateByAdminId(state,id);
    }
}
