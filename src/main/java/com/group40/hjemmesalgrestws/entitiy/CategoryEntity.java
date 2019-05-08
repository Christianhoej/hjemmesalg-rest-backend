package com.group40.hjemmesalgrestws.entitiy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity implements Serializable{

    private static final long serialVersionUID = 123454L;


    @Id
    @GeneratedValue
    private int categoryId;

    @Column(nullable = false, length = 50, unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")

    private List<AdEntity> ads;

    public List<AdEntity> getAds() {
        return ads;
    }

    public void setAds(List<AdEntity> ads) {
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
