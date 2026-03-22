package com.sht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Order.action")
public class OrderController {

    @GetMapping(params = "newOrderForm")
    public String newOrderForm() {
        return "order/NewOrderForm";
    }

    /**
     * 账单表单提交：勾选了"发货到不同地址" → 进入填写收货地址表单
     */
    @PostMapping(params = {"newOrder", "shippingAddressRequired"})
    public String newOrderWithShipping() {
        return "order/ShippingForm";
    }

    /**
     * 账单表单提交（未勾选不同地址）或收货地址表单提交 → 进入确认页
     */
    @PostMapping(params = {"newOrder", "!shippingAddressRequired"})
    public String newOrder() {
        return "order/ConfirmOrder";
    }

    /**
     * 确认页点击"Confirm" → 下单完成，展示订单详情
     */
    @GetMapping(params = {"newOrder", "confirmed"})
    public String confirmOrder() {
        return "order/ViewOrder";
    }

    @GetMapping(params = "listOrders")
    public String listOrders() {
        return "order/ListOrders";
    }

    @GetMapping(params = "viewOrder")
    public String viewOrder(@RequestParam("orderId") int orderId) {
        return "order/ViewOrder";
    }
}
