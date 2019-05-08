package com.group40.hjemmesalgrestws.io.controllers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.category.request.CategoryDetailsModel;
import com.group40.hjemmesalgrestws.io.models.category.response.CategoryRest;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("category") //http://localhost:8080/Homely-ws/category
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping()
    @CrossOrigin(origins = "*")
    public CategoryRest createCategory(@RequestBody CategoryDetailsModel category){
        CategoryRest returnValue = new CategoryRest();
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category,categoryDTO);
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        BeanUtils.copyProperties(createdCategory,returnValue);
        return returnValue;

    }

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<CategoryRest> getCategories(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "25") int limit) {

        if(page>0) page-=1;
        List<CategoryRest> returnValue = new ArrayList<CategoryRest>();
        List<CategoryDTO> categories = categoryService.getCategories(page, limit);

        for(CategoryDTO cat: categories ){
            CategoryRest categoryRest = new CategoryRest();
            BeanUtils.copyProperties(cat,categoryRest);
            returnValue.add(categoryRest);
        }
        return returnValue;
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public CategoryRest getCategory(@PathVariable String id){
        CategoryRest returnValue = new CategoryRest();

        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        BeanUtils.copyProperties(categoryDTO,returnValue);

        return returnValue;
    }
    @PutMapping(path="/{id}")
    @CrossOrigin(origins = "*")
    public CategoryRest updateCategory(@RequestBody CategoryDetailsModel newCategoryName, @PathVariable String id) {


        CategoryRest returnValue = new CategoryRest();

        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(newCategoryName,categoryDTO);

        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, id);
        //System.out.println(updatedCategory.get);
        BeanUtils.copyProperties(updatedCategory, returnValue);

        return returnValue;


    }
    @DeleteMapping(path="/{id}")
    @CrossOrigin(origins = "*")
    public DeleteStatusModel deleteCategory(@PathVariable String id) {
        DeleteStatusModel returnValue = new DeleteStatusModel();
        returnValue.setOperationName("Delete Category by categoryId: " + id + ": ");

        boolean succesDelete = categoryService.deleteCategoryById(id);
        if(succesDelete)
            returnValue.setOperationResult("SUCCES");
        else
            returnValue.setOperationResult("FAILED");

        return returnValue;

    }




}
