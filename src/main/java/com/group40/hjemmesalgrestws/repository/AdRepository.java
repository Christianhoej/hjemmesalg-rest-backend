package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends PagingAndSortingRepository<AdEntity, Integer> {
    AdEntity findByAdId(int id);

    //@Query("select AdEntity from ads where ads.category =: id")
    List<AdEntity> findAllByCategoryIsContaining(int id);

    //List<AdEntity> findAllByCategory();
    //AdEntity findAllByCategory(AdEntity pageable, String category);
}
