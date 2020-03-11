package com.project.draggerlogin.login;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("mail")
    private String mail;
    @SerializedName("password")
    private String password;

    public User(String mail,String password){
        this.mail = mail;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
