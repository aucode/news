package com.aucode.news.service.impl;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.aucode.news.entity.User;
import com.aucode.news.mapper.UserMapper;
import com.aucode.news.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户表 Mapper
     */
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUserInfo() {

        return userMapper.getAllUserInfo();
    }

    @Override
    public boolean verificationLoginInfo(User user, HttpSession session) {

        /*
            在数据库查找用户名
         */
        User loginInfo = userMapper.getUserInfoByName(user.getName());

        /*
           判断是否有该条用户数据
         */
        if (loginInfo == null || "".equals(loginInfo)){

            return false;
        }else{
            /*
                判断密码是否正确
             */
            if (loginInfo.getPassword().equals(user.getPassword())) {

                //保存用户登陆信息
                SessionUtil.saveUserLoginSession(loginInfo,session);
                return true;
            }
        }

        return false;
    }
}
