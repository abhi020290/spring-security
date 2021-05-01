package com.springboot.security.config;

import com.springboot.security.interceptor.AuthenticationInterceptor;
import com.springboot.security.interceptor.AuthorizationInterceptor;
import com.springboot.security.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/error");
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/error");
        registry.addInterceptor(loggingInterceptor).excludePathPatterns("/error");
    }
}
