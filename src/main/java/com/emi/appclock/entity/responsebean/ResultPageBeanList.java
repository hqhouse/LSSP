package com.emi.appclock.entity.responsebean;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class ResultPageBeanList<T,J> {

    private int success;
    private String msg;
    private List<J> datalist;
    private PageInfo<T> pageinfo;

    private T dataBean;

    public T getDataBean() {
        return dataBean;
    }

    public ResultPageBeanList setDataBean(T dataBean) {
        this.dataBean = dataBean;
        return this;
    }
    //    public ResultPageBeanList(int success, String msg, PageInfo<T> pageinfo,List<J> datalist){
//        this.success=success;
//        this.msg=msg;
//        this.pageinfo=pageinfo;
//        this.datalist=datalist;
//    }

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

    public List<J> getDatalist() {
        return datalist;
    }

    public ResultPageBeanList setDatalist(List<J> datalist) {
        this.datalist = datalist;
        return this;
    }

    public PageInfo<T> getPageinfo() {
        return pageinfo;
    }

    public ResultPageBeanList setPageinfo(PageInfo<T> pageinfo) {
        this.pageinfo = pageinfo;
        return this;
    }



    public static ResultPageBeanList success(){
        ResultPageBeanList msg = new ResultPageBeanList();
        msg.setSuccess(1);
        msg.setMsg("成功");
        return msg;
    }

    public static ResultPageBeanList fail(){
        ResultPageBeanList msg = new ResultPageBeanList();
        msg.setSuccess(0);
        msg.setMsg("系统异常");
        return msg;
    }

}
