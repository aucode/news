package com.aucode.news.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键自增
     */
      private String id;

    /**
     * 登录名
     */
    private String name;

    /**
     * 登陆密码
     */
    private String password;


}
