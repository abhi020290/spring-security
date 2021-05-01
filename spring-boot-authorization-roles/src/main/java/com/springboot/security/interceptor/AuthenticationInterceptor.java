package com.springboot.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Value("${CART.clientId}")
    String cartClientId;

    @Value("${CART.clientSecret}")
    String cartClientSecret;

    @Value("${CHECKOUT.clientId}")
    String checkoutClientId;

    @Value("${CHECKOUT.clientSecret}")
    String checkoutClientSecret;

    @Value("${client.name}")
    List<String> clientNameList;

    @Value("#{${rolesAllowedForClient}}")
    private Map<String, String> valuesMap;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AuthenticationInterceptor Interceptor preHandle method Invoked ************");
        Enumeration<String> headerNames = request.getHeaderNames();
        String clientName= request.getHeader("X-CLIENT");

        while (headerNames.hasMoreElements()) {

            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            if (headerName.equalsIgnoreCase("X-CLIENT")) {
                if (!clientNameList.contains(headerValue)) {
                    log.info("AuthenticationInterceptor failed X-CLIENT");
                    return false;
                }
                log.info("AuthenticationInterceptor success for X-CLIENT");
            } else if (headerName.equalsIgnoreCase("X-CLIENT-ID")) {
                if (clientName.equalsIgnoreCase("CART")) {
                    if (!cartClientId.equals(headerValue)) {
                        log.info("CART AuthenticationInterceptor for cart client Id");
                        return false;
                    }
                } else if (clientName.equalsIgnoreCase("CHECKOUT")) {
                    if (!checkoutClientId.equals(headerValue)) {
                        log.info("CHECKOUT AuthenticationInterceptor for checkoutClientId Id");
                        return false;
                    }
                }
            } else if (headerName.equalsIgnoreCase("X-CLIENT-SECRET")) {
                if (clientName.equalsIgnoreCase("CART")) {
                    if (!cartClientSecret.equals(headerValue)) {
                        log.info("CART AuthenticationInterceptor for cart client Id");
                        return false;
                    }
                } else if (clientName.equalsIgnoreCase("CHECKOUT")) {
                    if (!cartClientSecret.equals(headerValue)) {
                        log.info("CHECKOUT AuthenticationInterceptor for checkoutClientId Id");
                        return false;
                    }
                }
            }

        }
        //checkRoles(request);
        log.info("AuthenticationInterceptor success X-CLIENT-ID & X-CLIENT-SECRET");
        return true;
    }

}
