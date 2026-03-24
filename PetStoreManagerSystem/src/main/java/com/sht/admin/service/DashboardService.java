package com.sht.admin.service;

import com.sht.admin.pojo.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ItemService itemService;
    private final UserService userService;
    private final OrderService orderService;

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        // 与 index.html 中 th:text 使用的变量名一致
        stats.put("totalCategories", categoryService.count());
        stats.put("totalProducts", productService.count());
        stats.put("totalUsers", userService.count());
        stats.put("totalOrders", orderService.count());
        List<Order> recentOrders = orderService.listRecent(10);
        stats.put("recentOrders", recentOrders);
        return stats;
    }
}
