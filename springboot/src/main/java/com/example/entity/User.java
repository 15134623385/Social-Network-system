package com.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Account {

    private Integer id;
    private String username;
    private String salt;
    private String password;
    private String name;
    private String avatar;
    private String role;
    private String sex;
    private String phone;
    private String email;
    private String info;
    private String birth;

    private Integer blogCount;
    private Integer likesCount;
    private Integer collectCount;
}
