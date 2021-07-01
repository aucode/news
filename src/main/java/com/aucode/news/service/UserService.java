package com.aucode.news.service;

import com.aucode.news.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-09
 */
public interface UserService extends IService<User> {

    /**
     * 读取User 表的所有数据
     * @return
     */
    List<User> getAllUserInfo();

    /**
     * 校验登陆用户的信息
     * @param user
     * @param session
     * @return
     */
    boolean verificationLoginInfo(User user, HttpSession session);
}
