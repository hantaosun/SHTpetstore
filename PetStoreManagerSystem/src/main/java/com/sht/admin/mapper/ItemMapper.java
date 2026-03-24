package com.sht.admin.mapper;

import com.sht.admin.pojo.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ItemMapper {
    List<Item> listAll();
    List<Item> listByProduct(@Param("productId") String productId);
    Item findById(@Param("itemId") String itemId);
    int insertItem(Item item);
    int insertInventory(Item item);
    int updateItem(Item item);
    int updateInventory(Item item);
    int deleteItem(@Param("itemId") String itemId);
    int deleteInventory(@Param("itemId") String itemId);
    long count();
}
