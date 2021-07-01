package com.aucode.news.service.impl;

import com.aucode.news.entity.SysNewclass;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.aucode.news.entity.SysNews;
import com.aucode.news.mapper.SysNewsMapper;
import com.aucode.news.service.SysNewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-17
 */
@Service
public class SysNewsServiceImpl extends ServiceImpl<SysNewsMapper, SysNews> implements SysNewsService {
    @Autowired
    private SysNewsMapper newsMapper;

    /**
     * 添加文章
     *
     * @param sysNews
     * @return
     */
    @Override
    public String add(SysNews sysNews) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime ldt = LocalDateTime.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),df);
        sysNews.setNewstime(ldt);
        return newsMapper.insert(sysNews) != 0 ? "添加成功！":"添加失败！";
    }

    /**
     * 文章数据分页
     *
     * @param
     * @param
     * @return
     */
    @Override
    public IPage<SysNews> getPageNewsList(int pageNum, int pageSize) {

        Page<SysNews> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SysNews> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().orderByDesc("newstime");
        Page<SysNews> columnPage = newsMapper.selectPage(page,queryWrapper);
        return columnPage;
    }

    /**
     * 修改状态
     *
     * @param sysNews
     */
    @Override
    public String updateState(SysNews sysNews) {
        QueryWrapper<SysNews> wrapper = new QueryWrapper<>();
        wrapper.eq("newsid",sysNews.getNewsid());
        sysNews.setState(sysNews.getState() == 0 ? 1 : 0);
        return newsMapper.update(sysNews,wrapper) == 0 ? "状态修改失败！":"状态修改成功！";
    }

    /**
     * 读取前十数据
     *
     * @return
     */
    @Override
    public Page<SysNews> getTopNewsList() {
        Page<SysNews> page = new Page<>(1,30);
        QueryWrapper<SysNews> wrapper = new QueryWrapper<>();
        wrapper.select().orderByDesc("clickscount","newstime");
        Page<SysNews> columnPage = newsMapper.selectPage(page,wrapper);
        return columnPage;
    }

    /**
     * 类型文章
     *
     * @param sysNewclass
     * @return
     */
    @Override
    public IPage<SysNews> getNewsList(SysNewclass sysNewclass) {
        Page<SysNews> page = new Page<>(1,10000000);
        QueryWrapper<SysNews> wrapper = new QueryWrapper<>();
        wrapper.eq("classid",sysNewclass.getId());
        return newsMapper.selectPage(page,wrapper);
    }

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @Override
    public SysNews getBrowse(int id) {

        newsMapper.updateClicksCount(id);
        return newsMapper.selectById(id);
    }

    /**
     * 搜索文章数据
     *
     * @param search
     * @return
     */
    @Override
    public IPage<SysNews> getSearchNewsList(String search) {

        Page<SysNews> page = new Page<>(1,10000000);
        QueryWrapper<SysNews> wrapper = new QueryWrapper<>();
        wrapper.like("headtitle",search);

        return newsMapper.selectPage(page,wrapper);
    }
}
