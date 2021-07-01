package com.aucode.news.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aucode.news.entity.SysNewclass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
public interface SysNewclassService extends IService<SysNewclass> {

    /**
     * 获取栏目类型
     * @param key
     * @return
     */
    Page<SysNewclass> getcloumnClassByKey(String key, int pageNum, int pageSize);

    /**
     * 添加栏目类型
     * @param newclass
     * @return
     */
    String addCloumnClass(SysNewclass newclass);

    /**
     * 删除
     * @param newclass
     * @return
     */
    String columnCalssdel(SysNewclass newclass);

    /**
     * 修改
     * @param newclass
     * @return
     */
    String updateClass(SysNewclass newclass);

    /**
     * 全部类型数据
     * @return
     */
    List<SysNewclass> getAllColumnClassList();
}
