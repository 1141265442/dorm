package com.zxhan.core.msg;

public class SuccessTip extends Tip{
    public SuccessTip(Integer code,Object message){
        super.code=code;
        super.message=message;
    }

    public SuccessTip(){
        super.code=200;
        super.message="操作成功";
    }
}
