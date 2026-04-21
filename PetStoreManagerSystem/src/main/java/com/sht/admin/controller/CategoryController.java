package com.sht.admin.controller;

import com.sht.admin.pojo.Category;
import com.sht.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.listAll());
        model.addAttribute("activePage", "category");
        System.out.println("xixi");
        return "admin/category/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("category", null);
        model.addAttribute("activePage", "category");
        return "admin/category/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("activePage", "category");
        return "admin/category/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category, RedirectAttributes ra) {
        categoryService.save(category, false);
        ra.addFlashAttribute("message", "分类 [" + category.getCategoryId() + "] 新增成功");
        return "redirect:/admin/category/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Category category, RedirectAttributes ra) {
        categoryService.save(category, true);
        ra.addFlashAttribute("message", "分类 [" + category.getCategoryId() + "] 更新成功");
        return "redirect:/admin/category/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        categoryService.delete(id);
        ra.addFlashAttribute("message", "分类 [" + id + "] 已删除");
        return "redirect:/admin/category/list";
    }
}
