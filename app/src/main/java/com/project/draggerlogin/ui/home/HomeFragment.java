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
import com.project.draggerlogin.login.MemoryRepository;
import com.project.draggerlogin.retrofit.ApiClient;
import com.project.draggerlogin.retrofit.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ReclyclerAdapterMesRandonnees adapter;
    private List<Randonnee> mesRandonnees = new ArrayList<>();
    private ApiInterface apiInterface;
    Context context;
    private String mail;
    private String password;
    public HomeFragment(String mail,String password) {
        this.mail = mail;
        this.password = password;
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
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        System.out.println("-------------------------------------");
        System.out.println("User Randonnees");
        System.out.println("mail --> "+mail);
        System.out.println("password --> "+password);
        System.out.println("-------------------------------------");
        Call<String> call = apiInterface.getMesRandonnees(mail,password);

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

    private void parseRandonneeJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                System.out.println("test recu");

                mesRandonnees.add(new Randonnee(new Date(),"iut de laval"));
                adapter = new ReclyclerAdapterMesRandonnees(mesRandonnees);
                recyclerView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}