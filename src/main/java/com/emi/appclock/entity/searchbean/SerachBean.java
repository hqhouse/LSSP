package com.emi.appclock.entity.searchbean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value="报表查询字段")
public class SerachBean implements Serializable {
    @ApiModelProperty(required=true,value="")
    private String serchtext ;
    private String order;
    private String communityId;
    private String stime;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getSerchtext() {
        return serchtext;
    }

    public void setSerchtext(String serchtext) {
        this.serchtext = serchtext;
    }
}
