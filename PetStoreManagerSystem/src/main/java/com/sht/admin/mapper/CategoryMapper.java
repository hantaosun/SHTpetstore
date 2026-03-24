package com.sht.admin.mapper;

import com.sht.admin.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> listAll();
    Category findById(@Param("categoryId") String categoryId);
    int insert(Category category);
    int update(Category category);
    int deleteById(@Param("categoryId") String categoryId);
    long count();
}
