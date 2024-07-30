package com.example.entity;

import lombok.Data;

/**
 * 角色用户父类
 */
@Data
public class Account {
    private Integer id;
    /** 用户名 */
    private String username;
    /** 名称 */
    private String name;
    /* 盐字符串 */
    private String salt;
    /** 密码 */
    private String password;
    /** 角色标识 */
    private String role;
    /** 新密码 */
    private String newPassword;
    /** 头像 */
    private String avatar;
    /** Token */
    private String token;
}
