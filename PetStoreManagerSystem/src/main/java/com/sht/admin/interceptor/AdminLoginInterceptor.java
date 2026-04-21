package com.sht.admin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, @Nullable ModelAndView modelAndView) {
        if (modelAndView == null) {
            return;
        }
        String view = modelAndView.getViewName();
        if (view != null && view.startsWith("redirect:")) {
            return;
        }
        Object adminUser = request.getSession().getAttribute("adminUser");
        modelAndView.addObject("adminUserDisplay", adminUser != null ? adminUser.toString() : "");
    }
}
