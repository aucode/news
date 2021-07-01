package com.aucode.news.service;

import com.aucode.news.entity.SysNewclass;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aucode.news.entity.SysNews;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-17
 */
public interface SysNewsService extends IService<SysNews> {

    /**
     * 添加文章
     * @param sysNews
     * @return
     */
    String add(SysNews sysNews);

    /**
     * 文章数据分页
     * @param
     * @param
     * @return
     */
    IPage<SysNews> getPageNewsList(int pageNum, int pageSize);

    /**
     * 修改状态
     * @param sysNews
     */
    String updateState(SysNews sysNews);

    /**
     * 读取前十数据
     * @return
     */
    Page<SysNews> getTopNewsList();

    /**
     * 类型文章
     * @param sysNewclass
     * @return
     */
    IPage<SysNews> getNewsList(SysNewclass sysNewclass);

    /**
     * 文章详情
     * @param id
     * @return
     */
    SysNews getBrowse(int id);

    /**
     * 搜索文章数据
     * @param search
     * @return
     */
    IPage<SysNews> getSearchNewsList(String search);
}
