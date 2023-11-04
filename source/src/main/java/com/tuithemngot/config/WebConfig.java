package com.tuithemngot.config;

import com.tuithemngot.Interceptor.AdminInterceptor;
import com.tuithemngot.Interceptor.ClientInterceptor;
import com.tuithemngot.Interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/login");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/***").excludePathPatterns("/admin/login","/admin/chklogin");
        registry.addInterceptor(new ClientInterceptor()).addPathPatterns("/***");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
