package com.stylefeng.guns.modular.system.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ManagerUserDto {

    private String userId;

    /* 用户账号 */
    private String userNo;

    /* 用户姓名 */
    private String userName;

    private String userPhone;

    // 1:超级管理员 2：管理员
    private String userRole;

    /* 1：登录状态 2：退出状态 3：停用状态 */
    private Integer userStatus;

    private Date createTime;

    private Date loginTime;
}
