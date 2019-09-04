package com.stylefeng.guns.modular.system.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserSearchDto extends PageRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String deptId;
    private String timeLimit;
    private String beginTime;
    private String endTime;
}
