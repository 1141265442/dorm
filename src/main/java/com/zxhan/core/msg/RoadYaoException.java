package com.zxhan.core.msg;

import com.zxhan.core.msg.exception.IExceptionEnum;

public class RoadYaoException extends RuntimeException {

    private Integer code;
//    private String message;
    private String msg;

    public RoadYaoException(IExceptionEnum exceptionEnum){
        this.code=exceptionEnum.getCode();
//        this.message=exceptionEnum.getMessage();
        this.msg=exceptionEnum.getMessage();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

}
