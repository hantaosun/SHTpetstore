package com.sht.controller;

import com.sht.pojo.Category;
import com.sht.pojo.Item;
import com.sht.pojo.Product;
import com.sht.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 移除 @Data：Controller 不需要自动生成 getter/setter，避免参数污染
@Controller
@RequestMapping("/Catalog.action")
public class CatalogController extends AbstractController {

    // 仅保留注入的 Service 作为类成员（Spring Bean 是单例且线程安全的）
    @Autowired
    private CatalogService catalogService;

    // 全局常量：视图名（可选，提升可读性）
    private static final String VIEW_MAIN = "catalog/Main";
    private static final String VIEW_CATEGORY = "catalog/Category";
    private static final String VIEW_PRODUCT = "catalog/Product";
    private static final String VIEW_ITEM = "catalog/Item";
    private static final String VIEW_SEARCH = "catalog/SearchProducts";
    private static final String VIEW_ERROR = "common/Error";

    // ========== 核心改进：所有请求数据用局部变量 ==========
    @GetMapping
    public String main() {
        return VIEW_MAIN;
    }

    @GetMapping(params = "viewCategory")
    public String viewCategory(
            @RequestParam("categoryId") String categoryId,
            Model model
    ) {
        // 局部变量：仅当前请求有效，无并发问题
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            // 显式指定 Model Key（和 JSP 取值一致，可读性强）
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return VIEW_CATEGORY;
    }

    @GetMapping(params = "viewProduct")
    public String viewProduct(
            @RequestParam(name = "productId") String productId,
            Model model
    ) {
        if (productId != null) {
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            Product product = catalogService.getProduct(productId);
            model.addAttribute("itemList", itemList);
            model.addAttribute("product", product);
        }
        return VIEW_PRODUCT;
    }

    @GetMapping(params = "viewItem")
    public String viewItem(
            @RequestParam(name = "itemId") String itemId,
            Model model
    ) {
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();
        model.addAttribute("item", item);
        model.addAttribute("product", product);
        return VIEW_ITEM;
    }

    @GetMapping(params = "searchProducts")
    public String searchProducts(
            @RequestParam(name = "keyword") String keyword,
            Model model
    ) {
        if (keyword == null || keyword.length() < 1) {
            setMessage("Please enter a keyword to search for, then press the search button.",model);
            return VIEW_ERROR;
        } else {
            List<Product> productList = catalogService.searchProductList(keyword.toLowerCase());
            model.addAttribute("productList", productList);
            return VIEW_SEARCH;
        }
    }

    // 移除 clear() 方法：局部变量无需清空，请求结束自动销毁
}