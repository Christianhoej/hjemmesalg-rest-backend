package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.entitiy.CategoryEntity;
import com.group40.hjemmesalgrestws.repository.CategoryRepository;
import com.group40.hjemmesalgrestws.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService_Impl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDetails) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDetails, categoryEntity);
        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);
        CategoryDTO returnValue = new CategoryDTO();
        BeanUtils.copyProperties(storedCategoryDetails, returnValue);
        return returnValue;

        /*
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
        */
    }

    @Override
    public List<CategoryDTO> getCategories(int page, int limit) {
        List<CategoryDTO> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CategoryEntity> categoriesPage = categoryRepository.findAll(pageableRequest);
        List<CategoryEntity> categories = categoriesPage.getContent();

        for(CategoryEntity cat: categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(cat, categoryDTO);
            returnValue.add(categoryDTO);
        }
        return returnValue;
    }
}
