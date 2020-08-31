package com.emi.appclock.service.plat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import com.emi.appclock.entity.searchbean.PageParam;
import com.github.pagehelper.PageInfo;

public interface PlatService {
    ResultBeanObj addCaseInquiry(JSONArray jsonArray);
    ResultBeanObj getCaseInquiryResult(JSONObject jsonObject);
    PageInfo getCaseInquiryList(PageParam pageParam);
    ResultBeanObj deleteCaseInquiry(JSONObject jsonObject);
    ResultBeanObj updateCaseInquiry(JSONObject jsObject);
    ResultBeanObj ProtectPhone(JSONObject jsObject);
    ResultBeanObj getPhoneTime(JSONObject jsObject);
}
