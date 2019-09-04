package com.stylefeng.guns.common.constant.tips;

import com.stylefeng.guns.common.exception.BizExceptionEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorTip extends Tip {
    public ErrorTip(int code, String msg) {
        super(code, msg);
    }

    public ErrorTip(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.msg = bizExceptionEnum.getMsg();
    }
}
