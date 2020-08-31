package com.emi.appclock.until;

public class CacheKeyManage {

    /**
     * 获取虚拟号码key 值
     * @return
     */
    public static String getProtectPhoneKey(String phone) {
            return "phone" + phone;
        }

}
