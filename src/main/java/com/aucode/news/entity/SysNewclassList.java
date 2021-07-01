package com.aucode.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysNewclassList implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 类型主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 栏目id编号
     */
    private Integer classid;

    /**
     * 栏目分类名称
     */
    private String classcontent;


}
