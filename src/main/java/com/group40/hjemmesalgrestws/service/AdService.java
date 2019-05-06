package com.group40.hjemmesalgrestws.service;

import com.group40.hjemmesalgrestws.dtos.AdDTO;

import java.util.List;

public interface AdService {
    AdDTO createAd(AdDTO adDetails);

    List<AdDTO> getAllAds(int page, int limit);

    List<AdDTO> getCategoryAds(int page, int limit, String categoryName);

    AdDTO getAdByadId(String id);
}
