package com.group40.hjemmesalgrestws.dtos;

import java.io.Serializable;
import java.util.List;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 4231L;
    private int categoryId;
    private String categoryName;
    private List<AdDTO> ads;

    public List<AdDTO> getAds() {
        return ads;
    }

    public void setAds(List<AdDTO> ads) {
        this.ads = ads;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
