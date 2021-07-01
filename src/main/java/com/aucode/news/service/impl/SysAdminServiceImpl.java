package com.aucode.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.aucode.news.entity.SysAdmin;
import com.aucode.news.entity.SysNews;
import com.aucode.news.mapper.SysAdminMapper;
import com.aucode.news.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.aucode.news.util.MD5;
import com.aucode.news.util.SessionUtil;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-10
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {



    @Autowired
    private SysAdminMapper adminMapper;

    /**
     * 校验管理员登陆信息
     *
     * @param session
     * @param admin
     * @return
     */
    @Override
    public int verificationAdminLoginInfo(HttpSession session, SysAdmin admin) {
        /*
            1、判断管理员用户信息是否存在
            2、判断用户密码是否存在
            3、保存session信息

         */
        SysAdmin adminInfo = adminMapper.getAdminInfoByName(admin.getAdminName());

        if (adminInfo == null || "".equals(adminInfo)){

            return 0;
        }else if (adminInfo.getAdminPassword().equals(admin.getAdminPassword())){

            if (adminInfo.getAuthority().equals("2")){
                return 2;
            }else {

                SessionUtil.saveAdminLoginSession(session,adminInfo);
                return 1;
            }
        }
        return 0;
    }

    /**
     * 用户数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<SysAdmin> getPageColumnList(int pageNum, int pageSize) {
        Page<SysAdmin> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        Page<SysAdmin> columnPage = adminMapper.selectPage(page,queryWrapper);
        return columnPage;
    }

    /**
     * 添加
     *
     * @param admin
     * @return
     */
    @Override
    public String add(SysAdmin admin) {

        admin.setAdminPassword(MD5.md5Str(admin.getAdminPassword()));
        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name",admin.getAdminName());
        if (adminMapper.selectCount(wrapper) > 0) {
            return "添加失败，账号已存在！";
        }
        return adminMapper.insert(admin) == 0 ? "添加失败！" : "添加成功！";
    }

    /**
     * 修改状态
     *
     * @param state
     * @param id
     * @return
     */
    @Override
    public String updateByAdminId(int state, int id) {
        SysAdmin admin = new SysAdmin();
        admin.setId(id);
        admin.setAuthority(state+"");

        return adminMapper.updateById(admin) == 0 ? "状态修改失败！":"状态修改成功！";
    }

    /**
     * 修改密码
     *
     * @param admin
     * @return
     */
    @Override
    public String upadtePwd(SysAdmin admin) {
        admin.setAdminPassword(MD5.md5Str(admin.getAdminPassword()));
        return adminMapper.updateById(admin) == 0 ? "密码修改失败！":"密码修改成功！";
    }
}
