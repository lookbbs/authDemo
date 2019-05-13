package com.example.chaindemo.web.config;

import com.example.chaindemo.web.filter.RepeatedLoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        RepeatedLoginFilter filter = new RepeatedLoginFilter();
        registrationBean.setFilter(filter);

        List<String> urls = new ArrayList<>();
        urls.add("/login");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }
}
