package com.sht.admin.service;

import com.sht.admin.mapper.OrderMapper;
import com.sht.admin.pojo.LineItem;
import com.sht.admin.pojo.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    public List<Order> listAll() {
        return orderMapper.listAll();
    }

    public List<Order> listByStatus(String status) {
        if (status == null || status.isBlank() || "ALL".equals(status)) {
            return orderMapper.listAll();
        }
        return orderMapper.listByStatus(status);
    }

    public List<Order> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return orderMapper.listAll();
        }
        return orderMapper.search(keyword);
    }

    public Order findById(int orderId) {
        Order order = orderMapper.findById(orderId);
        if (order != null) {
            List<LineItem> lineItems = orderMapper.listLineItems(orderId);
            order.setLineItems(lineItems);
        }
        return order;
    }

    public void updateStatus(int orderId, String status) {
        orderMapper.updateStatus(orderId, status);
    }

    public void shipOrder(int orderId, String courier) {
        orderMapper.updateCourier(orderId, courier);
        orderMapper.updateStatus(orderId, "S");
    }

    @Transactional
    public void delete(int orderId) {
        orderMapper.deleteLineItems(orderId);
        orderMapper.deleteOrderStatus(orderId);
        orderMapper.deleteOrder(orderId);
    }

    public long count() {
        return orderMapper.count();
    }

    public List<Order> listRecent(int limit) {
        return orderMapper.listRecent(limit);
    }
}
