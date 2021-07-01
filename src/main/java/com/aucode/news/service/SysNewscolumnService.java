package com.aucode.news.service;

import com.aucode.news.entity.SysNewsColumnClass;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.aucode.news.entity.SysNewscolumn;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
public interface SysNewscolumnService extends IService<SysNewscolumn> {

    List<SysNewsColumnClass> gethomelist();
    /**
     * 获取栏目类型
     * @return
     */
    List<SysNewsColumnClass> getType();

    /**
     * 检查是否登录状态 并添加数据
     * @param session
     * @param newscolumn
     * @return
     */
    String addColumn(HttpSession session, SysNewscolumn newscolumn);


    IPage<SysNewscolumn> getPageColumnList(int pageNum, int pageSize);

    /**
     * 删除栏目根据id
     * @param session
     * @param id
     * @return
     */
    String delColumn(HttpSession session, int id);

    /**
     * 修改栏目根据id
     * @param session
     * @param
     * @return
     */
    String updateColumn(HttpSession session, SysNewscolumn newscolumn);

    /**
     * 全部栏目数据
     * @return
     */
    List<SysNewscolumn> getAllColumnList();
}
