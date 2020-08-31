package com.emi.appclock.controller.plat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import com.emi.appclock.entity.responsebean.ResultPageBean;
import com.emi.appclock.entity.searchbean.PageParam;
import com.emi.appclock.entity.searchbean.PlatSearchBean;
import com.emi.appclock.entity.searchbean.SerachBean;
import com.emi.appclock.service.plat.PlatService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/plat")
@Api(tags = "简律后台通用方法")
public class PlatController {

    @Autowired
    PlatService platService;

    @RequestMapping(value = "/addCaseInquiry", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "添加案件咨询")
    public ResultBeanObj addCaseInquiry(@RequestBody @ApiParam(name = "添加案件咨询", value = "", required = true) JSONArray jsonArray) {
        try {
            return platService.addCaseInquiry(jsonArray);
        }catch (Exception e){
           return  ResultBeanObj.fail(e.getMessage());
        }
    }

    @RequestMapping(value = "/getCaseInquiryResult", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "查询案件价格")
    public ResultBeanObj getCaseInquiryResult(@RequestBody @ApiParam(name = "查询案件价格", value = "{'region':地区,'case_type':案件类型：民商事案件，行政案件，刑事案件,'price':价格 }", required = true) JSONObject jsonObject) {
        try {
            return platService.getCaseInquiryResult(jsonObject);
        }catch (Exception e){
            return  ResultBeanObj.fail(e.getMessage());
        }
    }

    //居民列表
    @RequestMapping(value="/getCaseInquiryList" ,method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "案件价格列表")
    public ResultPageBean<Map> getCaseInquiryList(@RequestBody @ApiParam(name="分页对象",value="传入json格式",required=true) PageParam<PlatSearchBean> pb){

        PageInfo pageInfo= platService.getCaseInquiryList(pb);
        return ResultPageBean.success().setData(pageInfo);

    }

    @RequestMapping(value = "/deleteCaseInquiry", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "删除案件价格")
    public ResultBeanObj deleteCaseInquiry(@RequestBody @ApiParam(name = "json", value = "传入json格式", required = true) JSONObject jso  ) {
        ResultBeanObj obj=   platService.deleteCaseInquiry(jso);
        return obj;
    }


    @RequestMapping(value="/updateCaseInquiry" ,method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "修改案件价格")
    public ResultBeanObj updateCaseInquiry(@RequestBody @ApiParam(name="案件价格对象",value="传入json格式",required=true)JSONObject jsObject){
        ResultBeanObj res=  platService.updateCaseInquiry(jsObject);
        return  res;
    }
    @RequestMapping(value="/ProtectPhone" ,method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "号码保护，虚拟号码")
    public ResultBeanObj ProtectPhone(@RequestBody @ApiParam(name="号码保护，虚拟号码",value="传入json格式",required=true)JSONObject jsObject){
        ResultBeanObj res=  platService.ProtectPhone(jsObject);
        return  res;
    }

    @RequestMapping(value="/getPhoneTime" ,method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(httpMethod = "POST", value = "获取通话时长")
    public ResultBeanObj getPhoneTime(@RequestBody @ApiParam(name="号码保护，虚拟号码",value="传入json格式",required=true)JSONObject jsObject){
        //JSONObject jsObject=null;
        ResultBeanObj res=  platService.getPhoneTime(jsObject);
        return  res;
    }

}
