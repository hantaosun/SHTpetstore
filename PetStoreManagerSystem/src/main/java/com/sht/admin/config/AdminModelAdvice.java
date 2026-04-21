package com.sht.admin.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

/**
 * 保证所有后台页面 Model 中都有 activePage，避免侧边栏里 ${activePage == '...'} 因变量未绑定而触发 SpEL 异常（500）。
 */
@ControllerAdvice(basePackages = "com.sht.admin.controller")
public class AdminModelAdvice {

    @ModelAttribute
    public void defaultSidebarNav(Model model) {
        if (!model.containsAttribute("activePage")) {
            model.addAttribute("activePage", "");
        }
    }
}
