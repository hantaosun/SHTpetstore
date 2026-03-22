package com.sht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Cart.action")
public class CartController {

    @GetMapping
    public String viewCart() {
        return "cart/Cart";
    }

    @GetMapping(params = "addItemToCart")
    public String addItemToCart(@RequestParam(name="workingItemId") String workingItemId) {
        return "redirect:/Cart.action";
    }

    @GetMapping(params = "removeItemFromCart")
    public String removeItemFromCart(@RequestParam(name="cartItem") String cartItem) {
        return "redirect:/Cart.action";
    }

    @PostMapping(params = "updateCartQuantities")
    public String updateCartQuantities() {
        return "redirect:/Cart.action";
    }
}
