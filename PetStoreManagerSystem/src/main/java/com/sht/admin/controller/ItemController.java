package com.sht.admin.controller;

import com.sht.admin.pojo.Item;
import com.sht.admin.service.ItemService;
import com.sht.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ProductService productService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String productId, Model model) {
        model.addAttribute("items",
                productId != null && !productId.isBlank()
                        ? itemService.listByProduct(productId)
                        : itemService.listAll());
        model.addAttribute("products", productService.listAll());
        model.addAttribute("activePage", "item");
        return "admin/item/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("item", null);
        model.addAttribute("products", productService.listAll());
        model.addAttribute("activePage", "item");
        return "admin/item/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("products", productService.listAll());
        model.addAttribute("activePage", "item");
        return "admin/item/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item, RedirectAttributes ra) {
        if (item.getStatus() == null || item.getStatus().isBlank()) {
            item.setStatus("P");
        }
        itemService.insert(item);
        ra.addFlashAttribute("message", "SKU [" + item.getItemId() + "] 新增成功");
        return "redirect:/admin/item/list";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Item item, RedirectAttributes ra) {
        // 保留原有 status，前端表单未提交该字段
        Item existing = itemService.findById(item.getItemId());
        if (existing != null && (item.getStatus() == null || item.getStatus().isBlank())) {
            item.setStatus(existing.getStatus());
        }
        itemService.update(item);
        ra.addFlashAttribute("message", "SKU [" + item.getItemId() + "] 更新成功");
        return "redirect:/admin/item/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes ra) {
        itemService.delete(id);
        ra.addFlashAttribute("message", "SKU [" + id + "] 已删除");
        return "redirect:/admin/item/list";
    }
}
