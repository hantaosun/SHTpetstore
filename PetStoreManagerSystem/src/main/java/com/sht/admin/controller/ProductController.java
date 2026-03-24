package com.sht.admin.controller;

import com.sht.admin.pojo.Product;
import com.sht.admin.service.CategoryService;
import com.sht.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String categoryId, Model model) {
        model.addAttribute("products",
                categoryId != null && !categoryId.isBlank()
                        ? productService.listByCategory(categoryId)
                        : productService.listAll());
        model.addAttribute("categories", categoryService.listAll());
        model.addAttribute("activePage", "product");
        return "admin/product/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("product", null);
        model.addAttribute("categories", categoryService.listAll());
        return "admin/product/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.listAll());
        return "admin/product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, RedirectAttributes ra) {
        productService.save(product, false);
        ra.addFlashAttribute("message", "商品 [" + product.getProductId() + "] 新增成功");
        return "redirect:/admin/product/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product, RedirectAttributes ra) {
        productService.save(product, true);
        ra.addFlashAttribute("message", "商品 [" + product.getProductId() + "] 更新成功");
        return "redirect:/admin/product/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        productService.delete(id);
        ra.addFlashAttribute("message", "商品 [" + id + "] 已删除");
        return "redirect:/admin/product/list";
    }
}
