package com.stylefeng.guns.common.constant;

import static com.stylefeng.guns.common.constant.ResponseCode.SUCCESS;

import com.stylefeng.guns.common.constant.tips.Tip;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonResponse<T> extends Tip {
    private T data;

    private Long count;

    public static <T> CommonResponse<T> build(Integer code, String msg, T data, Long count) {
        return new CommonResponse<>(code, msg, data, count);
    }

    public static <T> CommonResponse<T> buildSuccess(T data) {
        return buildSuccess(data, 0L);
    }

    public static <T> CommonResponse<T> buildSuccess(T data, Long count) {
        return new CommonResponse<>(SUCCESS.getCode(), SUCCESS.getMsg(), data, count);
    }

    public CommonResponse() {
        super();
    }

    public CommonResponse(int code, String msg, T data, long count) {
        super(code, msg);
        this.data = data;
        this.count = count;
    }
}
