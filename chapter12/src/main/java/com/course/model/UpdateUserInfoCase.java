package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    private int id;
    private int userId;
    private String userName;
    private String sex;
    private int age;
    private int permission;
    private int isDelete;
    private String expected;

}
