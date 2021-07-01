package com.aucode.news.mapper;

import com.aucode.news.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-09
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 读取User 表的所有数据
     * @return
     */
    List<User> getAllUserInfo();

    /**
     * 根据用户名读取记录
     */
    User getUserInfoByName(String name);

}
