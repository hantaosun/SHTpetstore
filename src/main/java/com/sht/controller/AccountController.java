package com.sht.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Account.action")
public class AccountController {
    @Autowired
    HttpSession session;

    @GetMapping(params = "signonForm")
    public String signonForm() {
        return "account/SignonForm";
    }

    @PostMapping(params = "signon")
    public String signon(@RequestParam("username") String username, @RequestParam("password") String password) {
        return "redirect:/Catalog.action";
    }

    @GetMapping(params = "newAccountForm")
    public String newAccountForm() {
        return "account/NewAccountForm";
    }

    @PostMapping(params = "newAccount")
    public String newAccount() {
        return "redirect:/Catalog.action";
    }

    @GetMapping(params = "editAccountForm")
    public String editAccountForm() {
        return "account/EditAccountForm";
    }

    @PostMapping(params = "editAccount")
    public String editAccount() {
        return "redirect:/Account.action?editAccountForm=";
    }

    @GetMapping(params = "signoff")
    public String signoff() {
        return "redirect:/Catalog.action";
    }
}
