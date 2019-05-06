package com.group40.hjemmesalgrestws.service;

import com.group40.hjemmesalgrestws.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDetails);

    List<CategoryDTO> getCategories(int page, int limit);
}
