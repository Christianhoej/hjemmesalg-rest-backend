package com.group40.hjemmesalgrestws.dtos;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 4231L;
    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
