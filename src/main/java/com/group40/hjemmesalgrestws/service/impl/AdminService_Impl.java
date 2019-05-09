package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.AdminStatsDTO;
import com.group40.hjemmesalgrestws.service.AdService;
import com.group40.hjemmesalgrestws.service.AdminService;
import com.group40.hjemmesalgrestws.service.CategoryService;
import com.group40.hjemmesalgrestws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService_Impl implements AdminService {

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AdService adService;

    @Override
    public AdminStatsDTO getStatistics() {
        AdminStatsDTO returnValue = new AdminStatsDTO();
        returnValue.setAdsCount( adService.getAdsCount());

        returnValue.setAdByCategoriesList(adService.getCountOfAdsByCategory());
        returnValue.setCategoriesCount(returnValue.getAdByCategoriesList().size());
        returnValue.setUsersCount(userService.getUsersCount());
        return returnValue;
    }
}
