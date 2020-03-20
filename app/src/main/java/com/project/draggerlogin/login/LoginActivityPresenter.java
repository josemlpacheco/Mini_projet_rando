package com.project.draggerlogin.login;

import android.util.Log;

import androidx.annotation.Nullable;
import com.project.draggerlogin.retrofit.ApiClient;
import com.project.draggerlogin.retrofit.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {

    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;
    private ApiInterface apiInterface;


    public LoginActivityPresenter(LoginActivityMVP.Model model){
        this.model = model;
    }
    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view  = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view !=null) {
            if (view.getIdentifiant().trim().equals("") || view.getMdp().trim().equals("")) {
                view.showInputError();
            } else {
                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<String> call = apiInterface.loginUser(view.getIdentifiant(),view.getMdp());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("Bien", response.body());
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.i("onSuccess", response.body().toString());

                                String jsonresponse = response.body().toString();
                                parseLoginData(jsonresponse);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        view.showServerError();
                    }
                });
            }
        }
    }

    private void parseLoginData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                view.accueil();
            }else{
                view.showUserError();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();

        if (user == null) {
            if (view != null) {
                view.showUserNotAvaible();
            }
        } else {
            if (view != null) {
                view.setIdentifiant(user.getMail());
                view.setMdp(user.getPassword());
            }
        }
    }

    @Override
    public void addAccount() {
        view.account();
    }
}
