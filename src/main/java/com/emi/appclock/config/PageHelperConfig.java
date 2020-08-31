package com.emi.appclock.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageHelperConfig {
    @Bean
    public PageHelper getPageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("dialect","sqlserver");
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("pageSizeZero","true");
        properties.setProperty("reasonable","false");
        properties.setProperty("returnPageInfo","check");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
