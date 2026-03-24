package com.sht.mapper;

import com.sht.pojo.Product;

import java.util.List;

public interface ProductMapper {

  List<Product> getProductListByCategory(String categoryId);

  Product getProduct(String productId);

  List<Product> searchProductList(String keywords);

}