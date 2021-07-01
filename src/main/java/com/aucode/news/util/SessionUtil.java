package com.aucode.news.util;

import com.aucode.news.entity.SysAdmin;
import com.aucode.news.entity.User;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Session 工具类
 */
public class SessionUtil {

    final static String USER_LOGIN_KEY = "loginUserInfo";

    final static String ADMIN_LOGIN_KEY = "AdminLoginInfo";


    /**
     * 保存用户登陆信息
     * @param user
     * @param session
     */
    public static void saveUserLoginSession(User user, HttpSession session){
        session.setAttribute(USER_LOGIN_KEY,user);
    }
    /**
     * 保存管理员登陆信息
     * @param session
     */
    public static void saveAdminLoginSession(HttpSession session, SysAdmin admin){
        session.setAttribute(ADMIN_LOGIN_KEY,admin);
    }

    /**
     * 提出登录
     * @param session
     */
    public static void outAdminLoginSession(HttpSession session){
        session.removeAttribute(ADMIN_LOGIN_KEY);
    }
    /**
     * 读取用户登陆信息
     * @param session
     * @return
     */
    public static User getUserLoginSession(HttpSession session){

        return (User) session.getAttribute(USER_LOGIN_KEY);
    }

    /**
     * 管理员登陆
     * @param session
     */
    public static SysAdmin getAdminLoginSession(HttpSession session){
        return (SysAdmin) session.getAttribute(ADMIN_LOGIN_KEY);
    }

}
