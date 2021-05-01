package com.springboot.security.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

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

/*    @Autowired
    private ResourceInfo resourceInfo;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AuthorizationInterceptor Interceptor preHandle method Invoked ************");
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            //RolesAllowed annotation = AnnotationUtils.findAnnotation(method, RolesAllowed.class);
           /* if (annotation == null) {
                // prevent access to method wihout security restrictions
                throw new RuntimeException("Rights are not defined for this handler");
            }*/

            if (method.isAnnotationPresent(DenyAll.class)) {
                return false;
            }

            if (!method.isAnnotationPresent(PermitAll.class)) {
                if (method.isAnnotationPresent(RolesAllowed.class)) {
                    RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                    Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));
                    //Is user valid?
                    if (isUserAllowed(valuesMap, rolesSet)) {
                        log.info("AuthorizationInterceptor Interceptor roles allowed");
                        return true;
                    } else {
                        log.info("AuthorizationInterceptor Interceptor roles failed");
                    }
                }
            }
        }
        return true;
    }

    private boolean isUserAllowed(Map<String, String> valuesMap, Set<String> rolesSet) {
        boolean isAllowed = false;
        String cart = valuesMap.get("CART");
        String[] rolesAllowed = null;
        if (cart.contains(",")) {
            rolesAllowed = cart.split(",");
            Optional<String> any = Arrays.stream(rolesAllowed).filter(t -> rolesSet.contains(t)).findAny();
            isAllowed = any.isPresent();
            return isAllowed;
        } else {
           return rolesSet.contains(cart);
        }


    }

}
