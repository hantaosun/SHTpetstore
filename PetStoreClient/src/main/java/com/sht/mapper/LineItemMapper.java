package com.sht.mapper;

import com.sht.pojo.LineItem;

import java.util.List;

public interface LineItemMapper {

  List<LineItem> getLineItemsByOrderId(int orderId);

  void insertLineItem(LineItem lineItem);

}