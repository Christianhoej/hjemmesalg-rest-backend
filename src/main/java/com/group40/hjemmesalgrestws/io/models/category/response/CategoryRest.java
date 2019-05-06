package com.group40.hjemmesalgrestws.io.models.category.response;

public class CategoryRest {
    private String categoryName;
    private int id;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryname) {
        this.categoryName = categoryname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
