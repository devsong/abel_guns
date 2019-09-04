package com.stylefeng.guns.core.shiro;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id; // 主键ID
    private String account; // 账号
    private String name; // 姓名
    private Integer deptId; // 部门id
    private List<Integer> roleList; // 角色集
    private String deptName; // 部门名称
    private List<String> roleNames; // 角色名称集
}
