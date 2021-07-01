package com.aucode.news.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.aucode.news.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-10
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 校验管理员登陆信息
     * @param session
     * @param admin
     * @return
     */
    int verificationAdminLoginInfo(HttpSession session, SysAdmin admin);

    /**
     * 用户数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<SysAdmin> getPageColumnList(int pageNum, int pageSize);

    /**
     * 添加
     * @param admin
     * @return
     */
    String add(SysAdmin admin);

    /**
     * 修改状态
     * @param state
     * @param id
     * @return
     */
    String updateByAdminId(int state, int id);


    /**
     * 修改密码
     * @param admin
     * @return
     */
    String upadtePwd(SysAdmin admin);
}
