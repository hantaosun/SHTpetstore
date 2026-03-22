package com.sht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Catalog.action")
public class CatalogController {

    @GetMapping
    public String main() {
        return "catalog/Main";
    }

    @GetMapping(params = "viewCategory")
    public String viewCategory(@RequestParam("categoryId") String categoryId) {
        System.out.println("haha");
        return "catalog/Category";
    }

    @GetMapping(params = "viewProduct")
    public String viewProduct(@RequestParam(name="productId") String productId) {
        return "catalog/Product";
    }

    @GetMapping(params = "viewItem")
    public String viewItem(@RequestParam(name="itemId") String itemId) {
        return "catalog/Item";
    }

    @GetMapping(params = "searchProducts")
    public String searchProducts(@RequestParam(name="keyword") String keyword) {
        return "catalog/SearchProducts";
    }
}
