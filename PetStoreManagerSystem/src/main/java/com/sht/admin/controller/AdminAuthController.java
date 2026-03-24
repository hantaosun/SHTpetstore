package com.sht.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @GetMapping({"/login", ""})
    public String loginPage(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "admin/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            session.setAttribute("adminUser", username);
            return "redirect:/admin/index";
        }
        model.addAttribute("error", "用户名或密码错误");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes ra) {
        session.invalidate();
        ra.addFlashAttribute("message", "已安全退出");
        return "redirect:/admin/login";
    }
}
