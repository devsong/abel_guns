package com.stylefeng.guns.modular.system.dto;

import lombok.Data;

@Data
public class PageRequestDto {
    // 页号
    private Integer page;
    // 条目数
    private Integer limit;
}
