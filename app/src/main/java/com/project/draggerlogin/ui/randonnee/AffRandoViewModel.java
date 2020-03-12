package com.project.draggerlogin.ui.randonnee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AffRandoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AffRandoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rando fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}