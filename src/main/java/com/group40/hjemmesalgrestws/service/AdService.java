package com.group40.hjemmesalgrestws.service;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.dtos.CategoryDTO;

import java.util.List;

public interface AdService {
    AdDTO createAd(AdDTO adDetails);

    List<AdDTO> getAllAds(int page, int limit);

    List<AdDTO> getCategoryAds(int page, int limit, CategoryDTO categoryName);

    AdDTO getAdByadId(String id);

    AdDTO updateAd(AdDTO adDTO, String id);

    boolean deleteByAdId(String id);

    List<AdDTO> getUserAds(String email);
}
