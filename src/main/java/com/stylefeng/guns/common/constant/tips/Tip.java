package com.stylefeng.guns.common.constant.tips;

import com.stylefeng.guns.common.constant.ResponseCode;

import lombok.Data;

@Data
public abstract class Tip {
    protected int code;
    protected String msg;

    protected Tip() {
        code = ResponseCode.SUCCESS.getCode();
        msg = ResponseCode.SUCCESS.getMsg();
    }

    protected Tip(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
