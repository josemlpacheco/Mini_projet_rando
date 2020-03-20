package com.project.draggerlogin;

import com.project.draggerlogin.login.LoginActivityMVP;
import com.project.draggerlogin.login.LoginActivityPresenter;
import com.project.draggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public void init(){
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        user = new User("clemtest@gmail.com","Test12#$");

        when(mockedModel.getUser()).thenReturn(user);

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);

    }

    @Test
    public void noExistInteractionWithView(){
        presenter.getCurrentUser();

        verify(mockedView,never()).showUserNotAvaible();
    }

    @Test
    public void loadUserFromRepoWhenValidUserIsPresent(){

        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        verify(mockedModel,times(1)).getUser();

        verify(mockedView,times(1)).setIdentifiant("clemtest@gmail.com");
        verify(mockedView,times(1)).setMdp("Test12#$");
        verify(mockedView,never()).showUserNotAvaible();
    }

    @Test
    public void showErrorMessageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockedModel,times(1)).getUser();

        verify(mockedView,times(1)).showUserNotAvaible();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){

        when(mockedView.getIdentifiant()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(1)).getIdentifiant();
        verify(mockedView,never()).getMdp();
        verify(mockedView,times(1)).showInputError();

        when(mockedView.getIdentifiant()).thenReturn("clemtest@gmail.com");
        when(mockedView.getMdp()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(2)).getIdentifiant();
        verify(mockedView,times(1)).getMdp();
        verify(mockedView,times(2)).showInputError();
    }

}
