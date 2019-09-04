package com.stylefeng.guns.modular.system.dto;

import com.stylefeng.guns.common.persistence.model.Dict;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DictDto extends Dict {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private String detail;
}
