package com.aucode.news.mapper;

import com.aucode.news.entity.SysNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-17
 */
public interface SysNewsMapper extends BaseMapper<SysNews> {
    void updateClicksCount(int id);

}
