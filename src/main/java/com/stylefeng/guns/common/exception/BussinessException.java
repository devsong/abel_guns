package com.stylefeng.guns.common.exception;

/**
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
public class BussinessException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 友好提示的code码
    private int code;

    // 友好提示
    private String msg;

    // 业务异常跳转的页面
    private String urlPath;

    public BussinessException(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.msg = bizExceptionEnum.getMsg();
        this.urlPath = bizExceptionEnum.getUrlPath();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

}
