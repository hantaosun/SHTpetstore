package com.sht.admin.mapper;

import com.sht.admin.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> listAll();
    List<Product> listByCategory(@Param("categoryId") String categoryId);
    Product findById(@Param("productId") String productId);
    int insert(Product product);
    int update(Product product);
    int deleteById(@Param("productId") String productId);
    long count();
}
