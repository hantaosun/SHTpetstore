package com.sht.admin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Object adminUser = request.getSession().getAttribute("adminUser");
        if (adminUser != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
