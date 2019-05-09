package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

    CategoryEntity findByCategoryId(int parseInt);
    CategoryEntity findByCategoryName(String name);
    List<CategoryEntity> findAllBy();

}
