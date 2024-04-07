package com;


import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Configuration
public class FullPathWebMvcConfig extends WebMvcConfigurerAdapter{

    @Resource
    private List<FullPathInterceptor> interceptors= Collections.emptyList();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(!CollectionUtils.isEmpty(interceptors)) {
            for(HandlerInterceptor each: interceptors) {
                registry.addInterceptor(each).addPathPatterns("/**");
            }
            super.addInterceptors(registry);
        }
    }
}
