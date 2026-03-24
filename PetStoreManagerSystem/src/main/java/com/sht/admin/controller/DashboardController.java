package com.sht.admin.controller;

import com.sht.admin.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/index")
    public String index(Model model) {
        Map<String, Object> stats = dashboardService.getStats();
        stats.forEach(model::addAttribute);
        model.addAttribute("activePage", "dashboard");
        return "admin/index";
    }
}
