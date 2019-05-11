package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AdRepository extends PagingAndSortingRepository<AdEntity, Integer> {
    AdEntity findByAdId(int id);
    List<AdEntity> findAll();
    int countAllBy();
    int countAdEntitiesByCategory(int categoryId);


    //@Query("select AdEntity from ads where ads.category =: id")
    List<AdEntity> findAllByCategoryIsContaining(int id);


    //int getAdsCount();

    //List<AdEntity> findAllByCategory();
    //AdEntity findAllByCategory(AdEntity pageable, String category);
}
