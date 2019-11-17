package com.exercise.demo.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xdz
 * @Descrption: 实现spring类WebMvcConfigurer，创建配置类把拦截器添加到拦截器链中
 * @Date: 2019/11/17 22:06
 */
@Configuration
public class InterceptorTrainConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SourceAccessInterceptor()).addPathPatterns("/**").excludePathPatterns("/xx/**");
    }
}
