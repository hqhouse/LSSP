package com.emi.appclock.entity.responsebean;

import java.util.List;

public class ResultBeanList<T> {

    private int success;
    private String msg;

    private List<T> data;


    private ResultBeanList(){
    }

    private ResultBeanList(int success, String msg, List<T> data){
        this.success=success;
        this.msg=msg;


        if(data!=null){
            this.data=data;
        }

    }


    public List<T> getData() {
        return data;
    }

    public ResultBeanList setData(List<T> data) {
        this.data = data;
        return this;
    }

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

    public static ResultBeanList fail(String msg){
        ResultBeanList result = new ResultBeanList(0,msg,null);
        return result;
    }

    public static ResultBeanList success(){
        ResultBeanList msg = new ResultBeanList();
        msg.setSuccess(1);
        msg.setMsg("成功");
        return msg;
    }

    public static ResultBeanList fail(){
        ResultBeanList msg = new ResultBeanList();
        msg.setSuccess(0);
        msg.setMsg("系统异常");
        return msg;
    }

}
