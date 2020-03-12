package com.project.draggerlogin.ui.randonnee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddRandoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddRandoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rando fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}