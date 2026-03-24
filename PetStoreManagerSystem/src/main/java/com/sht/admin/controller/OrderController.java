package com.sht.admin.controller;

import com.sht.admin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String status,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        if (keyword != null && !keyword.isBlank()) {
            model.addAttribute("orders", orderService.search(keyword));
        } else {
            model.addAttribute("orders", orderService.listByStatus(status));
        }
        model.addAttribute("status", status);
        model.addAttribute("keyword", keyword);
        model.addAttribute("activePage", "order");
        return "admin/order/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        model.addAttribute("activePage", "order");
        return "admin/order/detail";
    }

    @PostMapping("/ship/{id}")
    public String ship(@PathVariable int id,
                       @RequestParam String courier,
                       RedirectAttributes ra) {
        orderService.shipOrder(id, courier);
        ra.addFlashAttribute("message", "订单 #" + id + " 已发货，物流：" + courier);
        return "redirect:/admin/order/detail/" + id;
    }

    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable int id,
                               @RequestParam String status,
                               RedirectAttributes ra) {
        orderService.updateStatus(id, status);
        ra.addFlashAttribute("message", "订单 #" + id + " 状态已更新");
        return "redirect:/admin/order/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes ra) {
        orderService.delete(id);
        ra.addFlashAttribute("message", "订单 #" + id + " 已删除");
        return "redirect:/admin/order/list";
    }
}
