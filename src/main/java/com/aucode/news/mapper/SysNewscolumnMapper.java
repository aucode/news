package com.aucode.news.mapper;

import com.aucode.news.entity.SysNewsColumnClass;
import com.aucode.news.entity.SysNewscolumn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
public interface SysNewscolumnMapper extends BaseMapper<SysNewscolumn> {

    /**
     * 获取栏目类型
     * @return
     */
    List<SysNewsColumnClass> getType();

    List<SysNewsColumnClass> gethomelist();

}
