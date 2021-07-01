package com.aucode.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAdmin implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键自增
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名称
     */
    private String adminName;

    /**
     * 登陆密码
     */
    private String adminPassword;

    /**
     * 权限
     */
    private String  authority;


}
