package com.group40.hjemmesalgrestws.io.models.ads.response;

import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.user.reponse.UserRest;

public class AdRest {
    private int adId;
    private String header;
    private String date;
    private String description;
    private int price;
    private String imageURL;
    //private CategoryDTO category;
    private String category;
    private UserDTO user;
/*
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
*/
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/

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
