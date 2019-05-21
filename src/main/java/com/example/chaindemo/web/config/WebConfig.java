package com.example.chaindemo.web.config;

import com.example.chaindemo.web.filter.RepeatedLoginFilter;
import com.example.chaindemo.web.log.HttpServletRequestReplacedFilter;
import com.example.chaindemo.web.log.LogHandlerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean httpServletRequestReplacedFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        // /* 是全部的请求拦截，和Interceptor的拦截地址/**区别开
        registration.addUrlPatterns("/*");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        RepeatedLoginFilter filter = new RepeatedLoginFilter();
        registrationBean.setFilter(filter);

        List<String> urls = new ArrayList<>();
        urls.add("/login");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外
        registry.addInterceptor(new LogHandlerInterceptor()).addPathPatterns("/**");
    }
}
