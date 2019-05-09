package com.group40.hjemmesalgrestws.io.models.administrator.response;

import java.util.List;

public class AdminStatisticsRest {
    private int categoriesCount;
    private int adsCount;
    private int usersCount;
    private List<CategoryAdStatsRest> adByCategoriesList;

    public int getCategoriesCount() {
        return categoriesCount;
    }

    public void setCategoriesCount(int categoriesCount) {
        this.categoriesCount = categoriesCount;
    }

    public int getAdsCount() {
        return adsCount;
    }

    public void setAdsCount(int adsCount) {
        this.adsCount = adsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<CategoryAdStatsRest> getAdByCategoriesList() {
        return adByCategoriesList;
    }

    public void setAdByCategoriesList(List<CategoryAdStatsRest> adByCategoriesList) {
        this.adByCategoriesList = adByCategoriesList;
    }
}
