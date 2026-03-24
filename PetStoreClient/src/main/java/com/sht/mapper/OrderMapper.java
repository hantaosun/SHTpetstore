package com.sht.mapper;

import com.sht.pojo.Order;

import java.util.List;

public interface OrderMapper {

  List<Order> getOrdersByUsername(String username);

  Order getOrder(int orderId);

  void insertOrder(Order order);

  void insertOrderStatus(Order order);

}