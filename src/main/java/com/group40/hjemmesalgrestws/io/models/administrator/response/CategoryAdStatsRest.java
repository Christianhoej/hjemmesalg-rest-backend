package com.group40.hjemmesalgrestws.io.models.administrator.response;

public class CategoryAdStatsRest {
    private String categoryName;
    private int numbers;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
}
