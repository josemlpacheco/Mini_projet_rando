package com.project.draggerlogin.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.draggerlogin.R;
import com.project.draggerlogin.retrofit.ApiClient_mes_randonnees;
import com.project.draggerlogin.retrofit.ApiInterface_mes_randonnees;
import com.project.draggerlogin.retrofit.ReclyclerAdapterMesRandonnees;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReclyclerAdapterMesRandonnees adapter;
    private List<Randonnee> mesRandonnees = new ArrayList<>();
    private ApiInterface_mes_randonnees apiInterface;
    Context context;
    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

       // ajouterRando();

        recyclerView = view.findViewById(R.id.recycleView);
        /*recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);*/

        adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        apiInterface = ApiClient_mes_randonnees.getApiClient().create(ApiInterface_mes_randonnees.class);

        Call<String> call = apiInterface.getMesRandonnees("clemtest@gmail.com","test55");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("raw-->: "+response.raw());
                System.out.println("body-->: "+response.body());
                System.out.println("message-->: "+response.message());
                System.out.println("toString-->: "+response.toString());
                parseRandonneeJson(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("erreur");
                System.out.println("error -----> "+ t.toString());
            }
        });
        return view;
    }

    private void ajouterRando() {

        Call<String> call = apiInterface.getMesRandonnees("clemtest@gmail.com","test55");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("raw-->: "+response.raw());
                System.out.println("body-->: "+response.body());
                System.out.println("message-->: "+response.message());
                System.out.println("toString-->: "+response.toString());
                parseRandonneeJson(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("erreur");
                System.out.println("error -----> "+ t.toString());
            }
        });
    }


    private void parseRandonneeJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                System.out.println("test recu");

                mesRandonnees.add(new Randonnee(new Date(),"iut de laval"));
                adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
                recyclerView.setAdapter(adapter);
            }else{
                System.out.println("Enviando informacion");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}