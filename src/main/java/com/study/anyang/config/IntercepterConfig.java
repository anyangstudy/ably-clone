package com.study.anyang.config;

import com.study.anyang.common.interceptor.AccessLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLoggingInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/resources/**");
    }

    @Bean
    public HandlerInterceptor accessLoggingInterceptor() {
        return new AccessLoggingInterceptor();
    }
}
