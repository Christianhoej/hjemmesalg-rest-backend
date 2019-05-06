package com.group40.hjemmesalgrestws.entitiy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class CategoryEntity implements Serializable{

    private static final long serialVersionUID = 123454L;


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50, unique = true)
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
