package com.aucode.news.service.impl;

import com.aucode.news.entity.SysNewsColumnClass;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.aucode.news.entity.SysNewclass;
import com.aucode.news.entity.SysNewscolumn;
import com.aucode.news.mapper.SysNewclassMapper;
import com.aucode.news.mapper.SysNewscolumnMapper;
import com.aucode.news.service.SysNewscolumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
@Service
public class SysNewscolumnServiceImpl extends ServiceImpl<SysNewscolumnMapper, SysNewscolumn> implements SysNewscolumnService {

    @Autowired
    private SysNewscolumnMapper newscolumnMapper;

    @Autowired
    private SysNewclassMapper newclassMapper;




    /**
     *
     *
     * @return
     */
    @Override
    public List<SysNewsColumnClass> gethomelist() {
        return newscolumnMapper.gethomelist();
    }
    /**
     * 获取栏目类型
     *
     * @return
     */
    @Override
    public List<SysNewsColumnClass> getType() {
        return newscolumnMapper.getType();
    }

    /**
     * 检查是否登录状态 并添加数据
     *
     * @param session
     * @param newscolumn
     * @return
     */
    @Override
    public String addColumn(HttpSession session, SysNewscolumn newscolumn) {
        /*
            1、是否有重复数据
            2、添加数据

         */
        QueryWrapper<SysNewscolumn> wrapper = new QueryWrapper<>();
        wrapper.eq("content", newscolumn.getContent());
        SysNewscolumn sysNewscolumn = newscolumnMapper.selectOne(wrapper);

        if (sysNewscolumn == null || "".equals(sysNewscolumn)) {

            if (newscolumnMapper.insert(newscolumn) == 0){

                return "栏目添加失败！";
            }else {

                return "栏目添加成功！";
            }
        }

        return "重复添加，"+ newscolumn.getContent() +"栏目已存在。";
    }

    @Override
    public IPage<SysNewscolumn> getPageColumnList(int pageNum, int pageSize) {
        Page<SysNewscolumn> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SysNewscolumn> queryWrapper = new QueryWrapper<>();
        Page<SysNewscolumn> columnPage = newscolumnMapper.selectPage(page,queryWrapper);

        return columnPage;
    }

    /**
     * 删除栏目根据id
     *
     * @param session
     * @param id
     * @return
     */
    @Override
    public String delColumn(HttpSession session, int id) {
        QueryWrapper<SysNewclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("columnid",id);
        Integer count = newclassMapper.selectCount(queryWrapper);

        if (count == 0){

            return newscolumnMapper.deleteById(id) == 0 ? "删除失败！":"删除成功";
        }else{
            return "删除失败,该栏目下有子类型！";
        }
    }

    /**
     * 修改栏目根据id
     *
     * @param session
     * @param i
     * @return
     */
    @Override
    public String updateColumn(HttpSession session, SysNewscolumn newscolumn) {

        return newscolumnMapper.updateById(newscolumn) == 0 ? "修改失败！":"修改成功";
    }

    /**
     * 全部栏目数据
     *
     * @return
     */
    @Override
    public List<SysNewscolumn> getAllColumnList() {
        QueryWrapper<SysNewscolumn> wrapper = new QueryWrapper<>();
        return newscolumnMapper.selectList(wrapper);
    }


}
