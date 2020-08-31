package com.emi.appclock.mapper;

import com.emi.appclock.entity.searchbean.PlatSearchBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlatMapper {
    void addCaseInquiry(Map columnMap);
    List<Map> getCaseInquiry(Map map);
    List<Map> getCaseInquiryList(PlatSearchBean platSearchBean);
    void deleteCaseInquiry(int id);
    void updateCaseInquiry(Map map);
}
