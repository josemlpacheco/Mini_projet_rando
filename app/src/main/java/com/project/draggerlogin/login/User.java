package com.project.draggerlogin.login;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mail")
    private String mail;
    @SerializedName("password")
    private String password;
    @SerializedName("category")
    private String category;
    @SerializedName("phone")
    private String phone;
    @SerializedName("postalAddress")
    private String postalAddress;

    public User(String mail,String password){
        this.mail = mail;
        this.password = password;
    }

    public User(String firstName, String lastName, String mail, String password, String category, String phone, String postalAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.category = category;
        this.phone = phone;
        this.postalAddress = postalAddress;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }
}
