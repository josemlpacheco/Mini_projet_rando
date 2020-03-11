package com.project.draggerlogin.login;

public interface LoginRepository {

    public void saveUser(User user);
    public User getUser();

}
