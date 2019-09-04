package com.stylefeng.guns.modular.system.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 用户传输bean
 * 
 * @author stylefeng
 * @Date 2017/5/5 22:40
 */
@Data
public class UserDto {
    private Integer id;
    private String account;
    @JSONField(serialize=false)
    private String password;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Integer sex;
    private String email;
    private String phone;
    private String roleid;
    private Integer deptid;
    private Integer status;
    private Date createtime;
    private String avatar;
    // 角色名
    private String roleName;
    // 部门名
    private String deptName;
}
