package com.project.draggerlogin.login;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {
    @Provides
    public LoginActivityMVP.Presenter provideLoginActivityPresenter(LoginActivityMVP.Model model){
        return new LoginActivityPresenter(model);
    }

    @Provides
    public LoginActivityMVP.Model provideLoginActivityModel(LoginRepository repository) {
        return new LoginActivityModel(repository);
    }

    @Provides
    public LoginRepository provideLoginRepository(){
        return new MemoryRepository(); //cabmair aquí si queremos en lugar de un repositorio en memoria, une BD un cloud...
    }
}
