package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdRepository extends PagingAndSortingRepository<AdEntity, Integer> {
    AdEntity findByAdId(int id);
    //AdEntity findAllByCategory(AdEntity pageable, String category);
}
