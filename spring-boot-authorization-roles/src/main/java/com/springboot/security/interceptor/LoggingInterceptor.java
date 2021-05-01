package com.springboot.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LogInterceptor Interceptor PreHandle method Invoked ************");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        log.info("request method ={} request url ={} request body ={}", request.getMethod(), request.getRequestURL());//, getRequestBody(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("LogInterceptor Interceptor postHandle method Invoked ************");
        //int status = response.getStatus();
       // ServletOutputStream outputStream = response.getOutputStream();
       // String responseBody = outputStream.toString().replaceAll(" ", "");
       // log.info("response status  {} request url {} response body {}", status, request.getRequestURI(), responseBody);
    }

   // private String getRequestBody(HttpServletRequest request) throws IOException {
       /* BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            stringBuilder.append(new String(buffer));
            buffer = new char[10];
        }
        reader.close();

        String content = stringBuilder.toString().replaceAll(" ", "");*/
        //String content = null;
      //  InputStream requestInputStream = request.getInputStream();
      //  content = Arrays.toString(StreamUtils.copyToByteArray(requestInputStream));
      //  return content;
    //}
}
