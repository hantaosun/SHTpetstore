package com.sht.admin.controller;

import com.sht.admin.pojo.Account;
import com.sht.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("users", userService.search(keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("activePage", "user");
        return "admin/user/list";
    }

    @GetMapping("/detail/{username}")
    public String detail(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("activePage", "user");
        return "admin/user/detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Account account, RedirectAttributes ra) {
        userService.update(account);
        ra.addFlashAttribute("message", "用户信息已更新");
        return "redirect:/admin/user/detail/" + account.getUsername();
    }

    @PostMapping("/resetPassword/{username}")
    public String resetPassword(@PathVariable String username,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                RedirectAttributes ra) {
        if (!newPassword.equals(confirmPassword)) {
            ra.addFlashAttribute("message", "两次密码不一致，请重新输入");
        } else {
            userService.resetPassword(username, newPassword);
            ra.addFlashAttribute("message", "密码已重置成功");
        }
        return "redirect:/admin/user/detail/" + username;
    }
}
