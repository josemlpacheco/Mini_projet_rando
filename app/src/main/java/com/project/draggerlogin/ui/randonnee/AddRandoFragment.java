package com.project.draggerlogin.ui.randonnee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.project.draggerlogin.R;

public class AddRandoFragment extends Fragment {

    public AddRandoFragment() {
    }

    private AddRandoViewModel addrandoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addrando, container, false);
    }
}
