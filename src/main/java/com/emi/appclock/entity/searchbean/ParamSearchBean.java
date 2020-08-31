package com.emi.appclock.entity.searchbean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ParamSearchBean implements Serializable {

    @ApiModelProperty(required=true,value="主管编码")
    private String leadercode=null;
    @ApiModelProperty(required=true,value="状态")
    private String state=null;
    private String orgid="1";
    private String usercode=null;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getLeadercode() {
        return leadercode;
    }

    public void setLeadercode(String leadercode) {
        this.leadercode = leadercode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
