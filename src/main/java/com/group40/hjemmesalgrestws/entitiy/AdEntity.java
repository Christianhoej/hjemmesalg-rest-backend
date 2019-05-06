package com.group40.hjemmesalgrestws.entitiy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ads")
public class AdEntity implements Serializable {
    private static final long serialVersionUID = 123454L;


    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String header;
    @Column(nullable = false, length = 10)
    private String date;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(length = 10)
    private int price;
    @Column(nullable = false)
    private String imageURL;

    @Column
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
