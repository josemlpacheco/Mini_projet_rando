package com.project.draggerlogin.root;

import com.project.draggerlogin.login.LoginActivity;
import com.project.draggerlogin.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}
