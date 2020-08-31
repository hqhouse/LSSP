package com.emi.appclock.entity.responsebean;

import java.util.List;

public class ResultBeanAList<T,K> {

    private int success;
    private String msg;
    private T datat;
    private List<K> mlist;


    public List<K> getMlist() {
        return mlist;
    }

    public ResultBeanAList setMlist(List<K> mlist) {
        this.mlist = mlist;
        return this;
    }
    private ResultBeanAList(int success, String msg){
        this.success=success;
        this.msg=msg;
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

    public T getDatat() {
        return datat;
    }

    public ResultBeanAList setDatat(T datat) {
        this.datat = datat;
        return this;
    }

    public static ResultBeanAList success(){
        ResultBeanAList msg = new ResultBeanAList(1, "成功");
        return msg;
    }
    public static ResultBeanAList fail(String failmsg){
        ResultBeanAList msg = new ResultBeanAList(0, failmsg);
        return msg;
    }
    public static ResultBeanAList fail(){
        ResultBeanAList msg = new ResultBeanAList(0,"系统异常");
        return msg;
    }

}
