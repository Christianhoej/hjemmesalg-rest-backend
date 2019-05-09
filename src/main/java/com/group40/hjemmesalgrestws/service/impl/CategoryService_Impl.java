package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.entitiy.CategoryEntity;
import com.group40.hjemmesalgrestws.exceptions.CategoryServiceException;
import com.group40.hjemmesalgrestws.exceptions.ErrorFixes;
import com.group40.hjemmesalgrestws.exceptions.ErrorMessages;
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

        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryDetails.getCategoryName());
        if(categoryEntity!=null) throw new CategoryServiceException(ErrorMessages.CATEGORY_ALREADY_EXIST.getErrorMessage(), ErrorFixes.CATEGORY_ALREADY_EXIST.getErrorFix());
        categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(categoryDetails, categoryEntity);
        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);
        CategoryDTO returnValue = new CategoryDTO();
        BeanUtils.copyProperties(storedCategoryDetails, returnValue);
        return returnValue;

    }


    @Override
    public List<CategoryDTO> getCategories(int page, int limit) {
        List<CategoryDTO> returnValue = new ArrayList<>();

        /*Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CategoryEntity> categoriesPage = categoryRepository.findAll(pageableRequest);
        List<CategoryEntity> categories = categoriesPage.getContent();*/

        List<CategoryEntity> categoryEntities = categoryRepository.findAllBy();
        CategoryDTO andet = new CategoryDTO();
        for(CategoryEntity cat: categoryEntities){
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(cat, categoryDTO);
            if(categoryDTO.getCategoryName().equals("Andet")){
                andet = categoryDTO;
                continue;
            }
            else {
                returnValue.add(categoryDTO);
            }
        }
        if(andet.getCategoryName()!=null)
        returnValue.add(andet);
        return returnValue;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String id) {
        CategoryDTO returnValue = new CategoryDTO();

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(Integer.parseInt(id));

        if(categoryEntity==null) throw new CategoryServiceException(ErrorMessages.CATEGORY_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.CATEGORY_DOES_NOT_EXIST.getErrorFix());
        categoryEntity.setCategoryName(categoryDTO.getCategoryName());


        CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
        BeanUtils.copyProperties(updatedCategory,returnValue);
        return returnValue;
    }

    @Override
    public boolean deleteCategoryById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(Integer.parseInt(id));

        if(categoryEntity== null)  throw new CategoryServiceException(ErrorMessages.CATEGORY_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.CATEGORY_BY_ID_DOES_NOT_EXIST.getErrorFix());
        categoryRepository.delete(categoryEntity);

        return true;
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        CategoryDTO returnValue = new CategoryDTO();
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(Integer.parseInt(id));
        if(categoryEntity== null) throw new CategoryServiceException(ErrorMessages.CATEGORY_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.CATEGORY_BY_ID_DOES_NOT_EXIST.getErrorFix());

        BeanUtils.copyProperties(categoryEntity,returnValue);
        return returnValue;
    }

    @Override
    public CategoryDTO getCatgoryByName(String categoryName) {
        CategoryDTO returnValue = new CategoryDTO();
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryName);
        if(categoryEntity== null)  throw new CategoryServiceException(ErrorMessages.CATEGORY_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.CATEGORY_BY_NAME_DOES_NOT_EXIST.getErrorFix());

        BeanUtils.copyProperties(categoryEntity,returnValue);
        return returnValue;    }

}
