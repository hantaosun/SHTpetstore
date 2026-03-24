package com.sht.admin.mapper;

import com.sht.admin.pojo.LineItem;
import com.sht.admin.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> listAll();
    List<Order> listByStatus(@Param("status") String status);
    List<Order> search(@Param("keyword") String keyword);
    Order findById(@Param("orderId") int orderId);
    List<LineItem> listLineItems(@Param("orderId") int orderId);
    int updateStatus(@Param("orderId") int orderId, @Param("status") String status);
    int updateCourier(@Param("orderId") int orderId, @Param("courier") String courier);
    int deleteOrder(@Param("orderId") int orderId);
    int deleteOrderStatus(@Param("orderId") int orderId);
    int deleteLineItems(@Param("orderId") int orderId);
    long count();
    List<Order> listRecent(@Param("limit") int limit);
}
