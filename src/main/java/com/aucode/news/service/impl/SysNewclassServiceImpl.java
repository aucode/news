package com.aucode.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.aucode.news.entity.SysNewclass;
import com.aucode.news.entity.SysNews;
import com.aucode.news.mapper.SysNewclassMapper;
import com.aucode.news.mapper.SysNewsMapper;
import com.aucode.news.service.SysNewclassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SysNewclassServiceImpl extends ServiceImpl<SysNewclassMapper, SysNewclass> implements SysNewclassService {

    @Autowired
    SysNewclassMapper newclassMapper;


    @Autowired
    SysNewsMapper newsMapper;

    /**
     * 获取栏目类型
     *
     * @param key
     * @return
     */
    @Override
    public Page<SysNewclass> getcloumnClassByKey(String key,int pageNum, int pageSize) {



        Page<SysNewclass> page = new Page<>(pageNum,pageSize);

        QueryWrapper<SysNewclass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("columnid",key);
        Page<SysNewclass> columnPage = newclassMapper.selectPage(page,queryWrapper);


        return columnPage;
    }

    /**
     * 添加栏目类型
     *
     * @param newclass
     * @return
     */
    @Override
    public String addCloumnClass(SysNewclass newclass) {

        QueryWrapper<SysNewclass> wrapper = new QueryWrapper<>();
        wrapper.eq("content",newclass.getContent());
        if (newclassMapper.selectCount(wrapper) <= 0) {

            if (newclassMapper.insert(newclass) != 0){
                return "添加成功！";
            }else {
                return "添加失败！";
            }
        }else {
            return "栏目类型重复！";
        }


    }

    /**
     * 删除
     *
     * @param newclass
     * @return
     */
    @Override
    public String columnCalssdel(SysNewclass newclass) {

        QueryWrapper<SysNews> selectWrapper = new QueryWrapper<>();
        selectWrapper.eq("classid",newclass.getId());
        Integer count = newsMapper.selectCount(selectWrapper);

        if (count <= 0){
            QueryWrapper<SysNewclass> delwrapper = new QueryWrapper<>();
            delwrapper.eq("id",newclass.getId());
            return newclassMapper.delete(delwrapper) == 0 ? "删除失败！":"删除成功！";
        }

        return "删除失败该类型有子数据项！";
    }

    /**
     * 修改
     *
     * @param newclass
     * @return
     */
    @Override
    public String updateClass(SysNewclass newclass) {

        QueryWrapper<SysNewclass> wrapper = new QueryWrapper<>();
        wrapper.eq("content",newclass.getContent());
        Integer count = newclassMapper.selectCount(wrapper);
        if (count == 0){

            return newclassMapper.updateById(newclass) == 0 ? "修改失败！" : "修改成功！";
        }else {
            return "修改失败，类型存在！";
        }

    }

    /**
     * 全部类型数据
     *
     * @return
     */
    @Override
    public List<SysNewclass> getAllColumnClassList() {
        QueryWrapper<SysNewclass> wrapper = new QueryWrapper<>();

        return newclassMapper.selectList(wrapper);
    }
}
