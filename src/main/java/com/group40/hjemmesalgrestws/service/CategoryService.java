package com.group40.hjemmesalgrestws.service;

import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDetails);

    List<CategoryDTO> getCategories(int page, int limit);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, String id);

    boolean deleteCategoryById(String id);

    CategoryDTO getCategoryById(String id);

    CategoryDTO getCatgoryByName(String categoryName);
}
