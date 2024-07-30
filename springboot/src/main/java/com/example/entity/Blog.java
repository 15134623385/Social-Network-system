package com.example.entity;

/**
 * 博客信息
 */
import lombok.Data;

@Data
public class Blog {

    /** ID */
    private Integer id;
    /** 标题 */
    private String title;
    /** 内容 */
    private String content;
    /** 简介 */
    private String descr;
    /** 封面 */
    private String cover;
    /** 标签 */
    private String tags;
    /** 发布人ID */
    private Integer userId;
    /** 发布日期 */
    private String date;
    /** 浏览量 */
    private Integer readCount;
    private Integer categoryId;

    private String categoryName;
    private String userName;

    private User user;

    private Integer likesCount;

    private Boolean userLike; // 返回当前浏览的数据是否被当前登录的用户点赞

    private Integer collectCount;
    private Boolean userCollect;
}