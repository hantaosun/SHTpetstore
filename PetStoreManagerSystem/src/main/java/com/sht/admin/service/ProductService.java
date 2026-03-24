package com.sht.admin.service;

import com.sht.admin.mapper.ProductMapper;
import com.sht.admin.pojo.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> listAll() {
        return productMapper.listAll();
    }

    public List<Product> listByCategory(String categoryId) {
        return productMapper.listByCategory(categoryId);
    }

    public Product findById(String productId) {
        return productMapper.findById(productId);
    }

    public void save(Product product, boolean isEdit) {
        if (isEdit) {
            productMapper.update(product);
        } else {
            productMapper.insert(product);
        }
    }

    public void delete(String productId) {
        productMapper.deleteById(productId);
    }

    public long count() {
        return productMapper.count();
    }
}
