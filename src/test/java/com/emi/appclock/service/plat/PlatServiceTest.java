package com.emi.appclock.service.plat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatServiceTest {
    @Autowired
    PlatService platService;
    @Test
    public void protectPhone() {

        Map map=new HashMap<>();
        map.put("bParty","15162771958");
        map.put("aParty","18806290437");
        String json = JSON.toJSONString(map);//map转String
        JSONObject jsonObject = JSON.parseObject(json);
        ResultBeanObj  obj=platService.ProtectPhone(jsonObject);
        System.out.println(obj);
    }
}
