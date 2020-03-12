package com.project.draggerlogin.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.draggerlogin.R;
import com.project.draggerlogin.retrofit.ApiClient_mes_randonnees;
import com.project.draggerlogin.retrofit.ApiInterface_mes_randonnees;
import com.project.draggerlogin.retrofit.ReclyclerAdapterMesRandonnees;
import com.project.draggerlogin.ui.randonnee.AddRandoFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient_mes_randonnees.getApiClient().create(ApiInterface_mes_randonnees.class);

        Call<JSONObject> call = apiInterface.getMesRandonnees("jose@gmail.com","Qwerty1234.");

        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                System.out.println("raw-->: "+response.raw());
                System.out.println("body-->: "+response.body().toString());
                System.out.println("message-->: "+response.message());
                System.out.println("toString-->: "+response.toString());
                parseRandonneeJson(response.body());
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                System.out.println("erreur");
                System.out.println("error -----> "+ t.toString());
            }
        });
        return view;
    }

    private void parseRandonneeJson(JSONObject response) {
        try {
            if (response.getString("status").equals("true")) {
                System.out.println("Enviando informacion");
                mesRandonnees.add(new Randonnee(new Date(),"iut de laval"));
                adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
                recyclerView.setAdapter(adapter);
            }else{
                System.out.println("No funciona pero almenos lo muestra");
                mesRandonnees.add(new Randonnee(new Date(),"iut de laval"));
                adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
            }
            System.out.println("No funciona pero almenos lo muestra afuerita");
            mesRandonnees.add(new Randonnee(new Date(),"iut de laval"));
            adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}