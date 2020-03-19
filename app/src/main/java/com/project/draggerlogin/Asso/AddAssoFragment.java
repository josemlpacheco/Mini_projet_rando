package com.project.draggerlogin.Asso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.project.draggerlogin.R;

public class AddAssoFragment extends Fragment {
    public AddAssoFragment() {
    }
    //private AddAssoViewModel addAssoViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addasso, container, false);
    }
}
