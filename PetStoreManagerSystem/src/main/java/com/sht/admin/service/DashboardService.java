package com.sht.admin.service;

import com.sht.admin.pojo.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        try {
            stats.put("totalCategories", categoryService.count());
            stats.put("totalProducts", productService.count());
            stats.put("totalUsers", userService.count());
            stats.put("totalOrders", orderService.count());
            List<Order> recentOrders = orderService.listRecent(10);
            stats.put("recentOrders", recentOrders != null ? recentOrders : Collections.emptyList());
        } catch (Exception e) {
            log.error("仪表盘统计数据加载失败（请检查数据库是否启动、库名/表名是否与 application.yml 一致）", e);
            stats.put("totalCategories", 0L);
            stats.put("totalProducts", 0L);
            stats.put("totalUsers", 0L);
            stats.put("totalOrders", 0L);
            stats.put("recentOrders", Collections.emptyList());
            stats.put("dashboardError", "无法连接数据库或查询失败，请检查 MySQL 与 application.yml 配置。详情见控制台日志。");
        }
        return stats;
    }
}
