package com.sht.mapper;

import com.sht.pojo.Category;

import java.util.List;

public interface CategoryMapper {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);

}