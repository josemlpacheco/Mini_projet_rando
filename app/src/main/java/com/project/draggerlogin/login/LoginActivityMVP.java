package com.project.draggerlogin.login;

public interface LoginActivityMVP {

    interface View {
        String getIdentifiant();
        String getMdp();

        void showUserNotAvaible();
        void showInputError();
        void showUserSaved();
        void showServerError();
        void accueil();
        void showUserError();
        void setIdentifiant(String identifiant);
        void setMdp(String mdp);
        void account();
        void showEmailValidateError();
        void showPasswordValidateError();
        void validateDone();

    }

    interface Presenter {
        void setView(LoginActivityMVP.View view);

        void loginButtonClicked();

        void getCurrentUser();

        void addAccount();
    }

    interface Model{
        void createUser(String mail,String mdp);

        User getUser();
    }
}
