package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

}
