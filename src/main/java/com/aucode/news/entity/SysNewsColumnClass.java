package com.aucode.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;

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
public class SysNewsColumnClass implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "columnid", type = IdType.AUTO)
    private Integer columnid;

    /**
     * 栏目名称，限制10个汉字
     */
    private String content;

    private ArrayList<SysNewclassList> list;


    private ArrayList<SysNewsList> listnews;


}
