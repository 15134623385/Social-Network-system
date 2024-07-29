package com.example.entity;

/**
 * 内推管理对象 internalReferral
 * 
 */
public class InternalReferral {
    /** 内推id */
    private Integer id;

    /** 内推标题 */
    private String title;

    /** 内推标签 */
    private String tag;

    /** 内推内容 */
    private String content;

    /** 内推点赞数 */
    private Integer likes;

    /** 内推发表时间 */
    private String date;

    /** 内推阅读量 */
    private Long readCount;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String gettitle() {
        return title;
    }

    public void settag(String tag) {
        this.tag = tag;
    }

    public String gettag() {
        return tag;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public String getcontent() {
        return content;
    }

    public void setlikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getlikes() {
        return likes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getReadCount() {
        return readCount;
    }

}