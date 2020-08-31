package com.emi.appclock.until;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class NesSendAliyun {
    //对应阿里云账号 ntaxxfc@163.com
    private static String accessKeyId="LTAIPQWPVrBmBqBR";
    private static String secret="HMkfh5oz5E0e36KiohLtGtjDiO2eeg";
    private static String regionId="default";

    private static String TEMPLATE_CODE_AUNT = "SMS_189216253";//找阿姨模板
    private static String TEMPLATE_SIGN_AUNT = "自在猪租房网";//找阿姨签名

    public static void sendMes(String phoneNumber,String[] msg){

        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phoneNumber);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",msg[0]);
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());

        request.putQueryParameter("SignName", TEMPLATE_SIGN_AUNT);
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE_AUNT);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }


}
