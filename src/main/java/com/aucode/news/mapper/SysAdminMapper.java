package com.aucode.news.mapper;

import com.aucode.news.entity.SysAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-10
 */
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 查找管理员用户信息
     * @param name
     * @return
     */
    SysAdmin getAdminInfoByName(String name);

}
