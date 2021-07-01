package com.aucode.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Du Yi Code
 * @since 2021-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysNewsList implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 新闻id
     */
      @TableId(value = "newsid", type = IdType.AUTO)
    private Integer newsid;

    /**
     * 栏目id编号
     */
    private Integer newscolumnid;

    /**
     * 栏目分类id编号
     */
    private Integer newsclassid;

    /**
     * 新闻标题限制50个汉字
     */
    private String headtitle;

    /**
     * 文章内容
     */
    private String newscontent;

    /**
     * 作者限制10个汉族
     */
    private String author;

    /**
     * 发布时间
     */
    private LocalDateTime newstime;

    /**
     * 点击次数
     */
    private Integer clickscount;

    /**
     * 是否置顶
     */
    private String top;

    /**
     * 状态正常：0，下架：1
     */
    private Integer state;


}
