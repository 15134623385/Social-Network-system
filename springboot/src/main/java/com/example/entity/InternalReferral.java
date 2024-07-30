package com.example.entity;

import lombok.Data;

/**
 * 内推管理对象 internalReferral
 * 
 */
@Data
public class InternalReferral {
    /** 内推id */
    private Integer id;

    /** 内推标题 */
    private String title;

    /** 内推内容 */
    private String content;

    /** 简介 */
    private String descr;

    /** 封面 */
    private String cover;

    /** 内推码 */
    private String irCode;

    /** 主办方联系方式 */
    private String contact;

    /** 开始时间 */
    private String start;

    /** 结束时间 */
    private String end;

    /** 内推阅读量 */
    private Long readCount;
}