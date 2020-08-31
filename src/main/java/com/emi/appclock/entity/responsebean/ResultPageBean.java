package com.emi.appclock.entity.responsebean;

import com.github.pagehelper.PageInfo;

public class ResultPageBean<T> {

    private int success;
    private String msg;
    private PageInfo<T> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageInfo<T> getData() {
        return data;
    }

    public ResultPageBean setData(PageInfo<T> data) {
        this.data = data;
        return this;
    }


    public static ResultPageBean success(){
        ResultPageBean msg = new ResultPageBean();
        msg.setSuccess(1);
        msg.setMsg("成功");
        return msg;
    }

    public static ResultPageBean fail(){
//        ResultPageBean msg = new ResultPageBean();
//        msg.setSuccess(0);
//        msg.setMsg("系统异常");
        return fail("系统异常");
    }

    public static ResultPageBean fail(String error){
        ResultPageBean msg = new ResultPageBean();
        msg.setSuccess(0);
        msg.setMsg(error);
        return msg;
    }
}
