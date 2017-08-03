package com.wxkj.readerfm.beans;

/**
 * Created by taosong on 17/6/5.
 */

public class BaseResposeInfo extends BaseEntity {

    private int code;

    private String msg;

    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
