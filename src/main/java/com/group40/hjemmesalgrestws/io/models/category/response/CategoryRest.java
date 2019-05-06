package com.group40.hjemmesalgrestws.io.models.category.response;

public class CategoryRest {
    private String categoryName;
    private int categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryname) {
        this.categoryName = categoryname;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
