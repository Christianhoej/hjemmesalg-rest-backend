package com.group40.hjemmesalgrestws.entitiy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 123454L;

    @Id
    @Column(length = 200, unique = true)
    private String email;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(nullable = false, length = 11)
    private String phonenumber;
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(nullable = false, length = 5)
    private String zipCode;
    @Column(nullable = false, length = 15, unique = true)
    private String userId; //offentligt id til at tilgå brugeren
    @Column(nullable = false, length = 50)
    private String address;
    @Column(nullable = false, length = 10)
    private String gender;
    @Column(nullable = false, length = 10)
    private String birthday;

    @OneToMany(mappedBy = "user"/*, targetEntity = AdEntity.class/*, cascade = CascadeType.ALL*/) //Cascade skulle anvendes til at persiste en liste af ads når en user persistes
    private List<AdEntity> ads;

    public List<AdEntity> getAds() {
        return ads;
    }

    public void setAds(List<AdEntity> ads) {
        this.ads = ads;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
