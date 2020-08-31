package com.emi.appclock.entity.searchbean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分页参数")
public class PageParam<T> {

    @ApiModelProperty(required=true,value="第几页")
    private int indexPage=1;//第几页

    private int pageSize=10;//每页大小

    @ApiModelProperty(value="查询条件")
    private T searchbean;

    public T getSearchbean() {
        return searchbean;
    }

    public void setSearchbean(T searchbean) {
        this.searchbean = searchbean;
    }

    public int getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(int indexPage) {
        this.indexPage = indexPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
