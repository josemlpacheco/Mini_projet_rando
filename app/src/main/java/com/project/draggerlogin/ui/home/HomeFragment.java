package com.project.draggerlogin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.project.draggerlogin.R;
import com.project.draggerlogin.retrofit.ApiClient;
import com.project.draggerlogin.retrofit.ApiClient_mes_randonnees;
import com.project.draggerlogin.retrofit.ApiInterface_mes_randonnees;
import com.project.draggerlogin.retrofit.ReclyclerAdapterMesRandonnees;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReclyclerAdapterMesRandonnees adapter;
    private List<Randonnee> mesRandonnees;
    private ApiInterface_mes_randonnees apiInterface;
    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //recyclerView = findViewById(R.id.recycleView);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient_mes_randonnees.getApiClient().create(ApiInterface_mes_randonnees.class);

        Call<List<Randonnee>> call = apiInterface.getMesRandonnees("jose@gmail.com","Qwerty1234.");

        call.enqueue(new Callback<List<Randonnee>>() {
            @Override
            public void onResponse(Call<List<Randonnee>> call, Response<List<Randonnee>> response) {
                System.out.println("raw-->: "+response.raw());
                System.out.println("body-->: "+response.body().toString());
                System.out.println("message-->: "+response.message());
                System.out.println("toString-->: "+response.toString());
                mesRandonnees = response.body();
                adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Randonnee>> call, Throwable t) {
                System.out.println("erreur");
                System.out.println("error -----> "+ t.toString());
            }
        });
        return view;
    }
}
