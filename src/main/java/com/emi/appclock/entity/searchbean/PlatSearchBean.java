package com.emi.appclock.entity.searchbean;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value="公共平台查询")
public class PlatSearchBean  implements Serializable {

    private String region;

    private String caseType;



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }
}
