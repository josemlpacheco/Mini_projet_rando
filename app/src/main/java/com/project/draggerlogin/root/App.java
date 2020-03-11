package com.project.draggerlogin.root;

import android.app.Application;

import com.project.draggerlogin.login.LoginModule;
import com.project.draggerlogin.root.DaggerApplicationComponent;
public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
