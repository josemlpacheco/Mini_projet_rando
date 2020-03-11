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
import static org.mockito.Mockito.verifyNoInteractions;
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

        user = new User("Antonio","Banderas");

        when(mockedModel.getUser()).thenReturn(user);

        //when(mockedView.getFirstName()).thenReturn("Antonio");
        //when(mockedView.getLastName()).thenReturn("Banderas");

        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);

    }

    @Test
    public void noExistInteractionWithView(){
        presenter.getCurrentUser();
        //verifyNoInteractions(mockedView);
        //verify(mockedView,times(1)).showUserNotAvaible();
        verify(mockedView,never()).showUserNotAvaible();
    }

    @Test
    public void loadUserFromRepoWhenValidUserIsPresent(){

        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //comprobamos la interactuacion con el modelo de datos
        verify(mockedModel,times(1)).getUser();

        //comprobamos la interactuacion con la vista
        verify(mockedView,times(1)).setFirstName("Antonio");
        verify(mockedView,times(1)).setLastName("Banderas");
        verify(mockedView,never()).showUserNotAvaible();
    }

    @Test
    public void showErrorMessageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //comprobamos la interactuacion con el modelo de datos
        verify(mockedModel,times(1)).getUser();

        //comprobamos la interactuacion con la vista
        verify(mockedView,times(1)).showUserNotAvaible();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){
        //primera prueba poniendo el campo firstName vacío
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(1)).getFirstName();
        verify(mockedView,never()).getLastName();
        verify(mockedView,times(1)).showInputError();

        //segunda prueba poniendo el valor en el campo firstName y dejando el campo lastName vacío
        when(mockedView.getFirstName()).thenReturn("Antonio");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView,times(2)).getFirstName(); //El método se llama dos veces
        verify(mockedView,times(1)).getLastName();
        verify(mockedView,times(2)).showInputError();
    }

    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("Jose");
        when(mockedView.getLastName()).thenReturn("Lopez");

        presenter.loginButtonClicked();

        //Debe haber dos llamadas, una en el if y otra en la creación de un usuario
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(2)).getLastName();

        //el modelo debe persistir en el repositorio
        verify(mockedModel,times(1)).createUser("Jose","Lopez");

        verify(mockedView,times(1)).showUserSaved();
    }

}
