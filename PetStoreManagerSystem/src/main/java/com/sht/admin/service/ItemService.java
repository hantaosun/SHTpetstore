package com.sht.admin.service;

import com.sht.admin.mapper.ItemMapper;
import com.sht.admin.pojo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

    public List<Item> listAll() {
        return itemMapper.listAll();
    }

    public List<Item> listByProduct(String productId) {
        return itemMapper.listByProduct(productId);
    }

    public Item findById(String itemId) {
        return itemMapper.findById(itemId);
    }

    @Transactional
    public void insert(Item item) {
        itemMapper.insertItem(item);
        itemMapper.insertInventory(item);
    }

    @Transactional
    public void update(Item item) {
        itemMapper.updateItem(item);
        itemMapper.updateInventory(item);
    }

    @Transactional
    public void delete(String itemId) {
        itemMapper.deleteInventory(itemId);
        itemMapper.deleteItem(itemId);
    }

    public long count() {
        return itemMapper.count();
    }
}
