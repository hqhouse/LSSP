package com.emi.appclock.controller;

import com.emi.appclock.entity.responsebean.ResultBeanList;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import com.emi.appclock.until.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuixn on 2019/3/16.
 */
@Controller
@RequestMapping(value="upload")
@Api(tags = "图片上传相关")
public class UploadController extends BaseController {

    //上传图片
    @RequestMapping("/uploadImg")
    @ApiOperation(httpMethod = "POST", value = "上传图片   返回结果为   {success:1,msg:'msg',data:imageUrl:'全地址',shortUrl:'短地址'} ")
    @ResponseBody
    public ResultBeanObj uploadImg(HttpServletRequest request) throws Exception{

        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartfiles = req.getFiles("file");
        MultipartFile multipartFile = req.getFile("file");

        //上传文件
        ResultBeanObj ResultBeanObj= UploadUtil.uploadFile(multipartFile,null,null);
        Map map=(Map)ResultBeanObj.getData();
        map.put("imageUrl", UploadUtil.getAuntOssUrl+map.get("imageUrl").toString());
        return ResultBeanObj.setData(map);

    }
    //上传多张图片
    @RequestMapping("/uploadImgMultipart")
    @ApiOperation(httpMethod = "POST", value = "上传图片   返回结果为   {success:1,msg:'msg',data:imageUrl:'全地址',shortUrl:'短地址'} ")
    @ResponseBody
    public ResultBeanList uploadImgMultipart(HttpServletRequest request) throws Exception{

        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartfiles = req.getFiles("file");

        List<Map> maps=new ArrayList<>();
        for(MultipartFile multipartFile:multipartfiles){
            //上传文件
            ResultBeanObj ResultBeanObj=UploadUtil.uploadFile(multipartFile,null,null);
            Map map=(Map)ResultBeanObj.getData();
            map.put("imageUrl",UploadUtil.getAuntOssUrl+map.get("imageUrl").toString());
            maps.add(map);
        }
        return ResultBeanList.success().setData(maps);
    }
    @RequestMapping("/uploadFile")
    @ApiOperation(httpMethod = "POST", value = "上传图片   返回结果为   {success:1,msg:'msg',data:imageUrl:'全地址',shortUrl:'短地址'} ")
    @ResponseBody
    public ResultBeanObj uploadFile(HttpServletRequest request) throws Exception{

        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        List<MultipartFile> multipartfiles = req.getFiles("file");
        MultipartFile multipartFile = req.getFile("file");

        //上传文件
        ResultBeanObj ResultBeanObj= UploadUtil.uploadTFile(multipartFile);
        Map map=(Map)ResultBeanObj.getData();
        map.put("imageUrl", UploadUtil.getAuntOssUrl+map.get("imageUrl").toString());
        return ResultBeanObj.setData(map);

    }
}
