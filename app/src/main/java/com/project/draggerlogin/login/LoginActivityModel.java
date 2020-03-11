package com.project.draggerlogin.login;

public class LoginActivityModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String identifiant,String mdp) {
        //lógica de business
        repository.saveUser(new User(identifiant,mdp));
    }

    @Override
    public User getUser() {
        //lógica de business
        return repository.getUser();
    }
}
