package com.emi.appclock.service.plat.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emi.appclock.config.redis.RedisUtil;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import com.emi.appclock.entity.searchbean.PageParam;
import com.emi.appclock.entity.searchbean.PlatSearchBean;
import com.emi.appclock.mapper.PlatMapper;
import com.emi.appclock.service.plat.PlatService;
import com.emi.appclock.until.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@Service
public class PlatServiceImpl implements PlatService {

    @Autowired
    PlatMapper platMapper;
    @Autowired
    RedisUtil redisUtil;
    @Override
    public ResultBeanObj addCaseInquiry(JSONArray jsonArray) {

        JSONObject temp = new JSONObject();
        temp.put("region",jsonArray.getJSONObject(0).get("region"));
        temp.put("case_type",jsonArray.getJSONObject(0).get("case_type"));
        List<Map> maps = platMapper.getCaseInquiry(temp);
        if(maps.size() > 0){
            return ResultBeanObj.fail( temp.get("region") + "已存在" + temp.get("case_type") + "的数据！" );
        }
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject jsObject = jsonArray.getJSONObject(i);
            Map<String, Object> objMap = new HashMap<>();
            objMap.put("region", jsObject.get("region"));
            objMap.put("case_type", jsObject.get("case_type"));
            objMap.put("calculation_rules", jsObject.get("calculation_rules"));
            objMap.put("section_type", jsObject.get("section_type"));
            objMap.put("section_min", jsObject.get("section_min"));
            objMap.put("section_max", jsObject.get("section_max"));
            objMap.put("section_min_count", jsObject.get("section_min_count"));
            objMap.put("section_max_count", jsObject.get("section_max_count"));
            objMap.put("stage", jsObject.get("stage"));
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("columnMap", objMap);
            platMapper.addCaseInquiry(columnMap);
        }
        return ResultBeanObj.success();
    }

    @Override
    public ResultBeanObj getCaseInquiryResult(JSONObject jsonObject) {
        List<Map> maps = platMapper.getCaseInquiry(jsonObject);
        if(maps.size() > 0){
            String caseType = jsonObject.get("case_type").toString();
            Integer price = Integer.parseInt(jsonObject.get("price").toString());
            JSONObject result = new JSONObject();
            result.put("case_type",jsonObject.get("case_type"));
            result.put("region",jsonObject.get("region"));
            result.put("price",jsonObject.get("price"));
            result.put("data",maps);
            if("刑事案件".equals(caseType)){
                result.put("priceMin",price.toString());
                result.put("priceMax",price.toString());
            }else {
                BigDecimal minPrice = new BigDecimal(0);
                BigDecimal maxPrice = new BigDecimal(0);
                for (Map map: maps) {
                    int sectionMin = Integer.parseInt(map.get("section_min").toString());
                    int sectionMax = Integer.parseInt(map.get("section_max").toString());
                    BigDecimal sectionMinCount =  new BigDecimal(map.get("section_min_count").toString()).stripTrailingZeros();
                    BigDecimal sectionMaxCount =  new BigDecimal(map.get("section_max_count").toString()).stripTrailingZeros();
                    String sectionType = map.get("section_type").toString();
                    if(sectionMax == 0 && price > sectionMin){//0表示没有上限
                        if("定额区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount);
                            maxPrice = maxPrice.add(sectionMaxCount);
                        }else if("浮动区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount.divide(new BigDecimal(100)).multiply(new BigDecimal(price - sectionMin)));
                            maxPrice = maxPrice.add(sectionMaxCount.divide(new BigDecimal(100)).multiply(new BigDecimal(price - sectionMin)));
                        }
                    }else if(price > sectionMin && price <= sectionMax){
                        if("定额区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount);
                            maxPrice = maxPrice.add(sectionMaxCount);
                        }else if("浮动区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount.divide(new BigDecimal(100)).multiply(new BigDecimal(price - sectionMin)));
                            maxPrice = maxPrice.add(sectionMaxCount.divide(new BigDecimal(100)).multiply(new BigDecimal(price - sectionMin)));
                        }
                    }else if(price > sectionMax && sectionMax!=0){
                        if("定额区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount);
                            maxPrice = maxPrice.add(sectionMaxCount);
                        }else if("浮动区间".equals(sectionType)){
                            minPrice = minPrice.add(sectionMinCount.divide(new BigDecimal(100)).multiply(new BigDecimal(sectionMax - sectionMin)));
                            maxPrice = maxPrice.add(sectionMaxCount.divide(new BigDecimal(100)).multiply(new BigDecimal(sectionMax - sectionMin)));
                        }
                    }
                }
                result.put("priceMin",minPrice.toString());
                result.put("priceMax",maxPrice.toString());
            }
            return ResultBeanObj.success().setData(result);
        }else {
            return ResultBeanObj.fail("未查到该地区案件价格！");
        }
    }

    @Override
    public PageInfo getCaseInquiryList(PageParam pageParam) {
        PageHelper.startPage(pageParam.getIndexPage(), pageParam.getPageSize());
        List<Map> list = platMapper.getCaseInquiryList((PlatSearchBean) pageParam.getSearchbean());
        return new PageInfo<Map>(list);
    }

    @Override
    public ResultBeanObj deleteCaseInquiry(JSONObject jsonObject) {
        Integer id = jsonObject.getInteger("Id");
        platMapper.deleteCaseInquiry(id);
        return ResultBeanObj.success();
    }

    @Override
    public ResultBeanObj updateCaseInquiry(JSONObject jsObject) {
        if (jsObject.containsKey("Id") && jsObject.get("Id")!= null){
            //把要插入的表的有效字段都封装到一个map中
            Map<String, Object> objMap = new HashMap<>();
            objMap.put("section_max", jsObject.get("section_max"));
            objMap.put("section_max_count", jsObject.get("section_max_count"));
            objMap.put("section_min", jsObject.get("section_min"));
            objMap.put("section_min_count", jsObject.get("section_min_count"));
            objMap.put("stage", jsObject.get("stage"));
            objMap.put("Id", jsObject.get("Id"));
            //一定要再定义一个map用来传递参数
            platMapper.updateCaseInquiry(objMap);
            return ResultBeanObj.success();
        }else {
            return ResultBeanObj.fail("Id不能为空");
        }
    }
    @Override
    public ResultBeanObj ProtectPhone(JSONObject jsObject) {
        String PhoneNoA=jsObject.getString("aParty");//被叫号码
        String PhoneNoB=jsObject.getString("bParty");//主叫号码
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",            // 地域ID
                UploadUtil.accessKeyId,        // 您的AccessKey ID
                UploadUtil.accessKeySecret);  // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dyplsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("BindAxb");
        // 绑定关系的过期时间。必须晚于当前时间1分钟以上。
        request.putQueryParameter("Expiration", DatesUtil.timePastTenSecond(1800));
        // 号码池Key
        request.putQueryParameter("PoolKey", "FC100000103706274");
        // AXB中的B号码
        request.putQueryParameter("PhoneNoB", PhoneNoB);
        // AXB中的A号码
        request.putQueryParameter("PhoneNoA", PhoneNoA);
        // 指定城市进行X号码的选号。
       // request.putQueryParameter("ExpectCity", "北京");
        // AXB中的X号码。未指定X号码时，将根据参数ExpectCity从指定号码池中随机指定一个号码作为X号码。
     //   request.putQueryParameter("PhoneNoX", "17000000000");
        // 是否需要针对该绑定关系产生的所有通话录制通话录音。
        request.putQueryParameter("IsRecordingEnabled", "false");
        try {

            String sysvcode= redisUtil.get(CacheKeyManage.getProtectPhoneKey(PhoneNoA.trim()+PhoneNoB.trim()));
            Object sysvcodes= redisUtil.get(CacheKeyManage.getProtectPhoneKey(PhoneNoB.trim()+PhoneNoA.trim()));
            if(!StringUtil.isNullObject(sysvcode)){
                return  ResultBeanObj.success().setData(sysvcode);
            }else if(!StringUtil.isNullObject(sysvcodes)){
                return  ResultBeanObj.success().setData(sysvcodes);
            }else {
                CommonResponse response = client.getCommonResponse(request);
                JSONObject object= JSON.parseObject(response.getData());
                Map map=object.getJSONObject("SecretBindDTO");
                map.get("Extension").toString();//被叫号码
                map.get("SecretNo").toString();//隐藏号码
                redisUtil.set(CacheKeyManage.getProtectPhoneKey(PhoneNoA.trim()+PhoneNoB.trim()),map.get("SecretNo").toString(),new Long(1800));
                // 处理请求结果
                return  ResultBeanObj.success().setData(map.get("SecretNo").toString());
            }
        } catch (ServerException e) {
            e.printStackTrace();
            return    ResultBeanObj.fail("系统错误");
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return   ResultBeanObj.fail(e.getErrMsg());
        }
    }

    @Override
    public ResultBeanObj getPhoneTime(JSONObject jsObject) {

        return null;
    }
}
