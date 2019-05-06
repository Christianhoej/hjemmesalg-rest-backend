package com.group40.hjemmesalgrestws.repository;

import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdRepository extends PagingAndSortingRepository<AdEntity, Integer> {
}
