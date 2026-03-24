package com.sht.admin.service;

import com.sht.admin.mapper.CategoryMapper;
import com.sht.admin.pojo.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> listAll() {
        return categoryMapper.listAll();
    }

    public Category findById(String categoryId) {
        return categoryMapper.findById(categoryId);
    }

    public void save(Category category, boolean isEdit) {
        if (isEdit) {
            categoryMapper.update(category);
        } else {
            categoryMapper.insert(category);
        }
    }

    public void delete(String categoryId) {
        categoryMapper.deleteById(categoryId);
    }

    public long count() {
        return categoryMapper.count();
    }
}
