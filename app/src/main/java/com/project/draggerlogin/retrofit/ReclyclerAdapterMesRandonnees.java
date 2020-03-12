package com.project.draggerlogin.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.draggerlogin.R;
import com.project.draggerlogin.ui.home.Randonnee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReclyclerAdapterMesRandonnees extends RecyclerView.Adapter<ReclyclerAdapterMesRandonnees.MyViewHolder> {
    private List<Randonnee> mesRandonnees;

    public ReclyclerAdapterMesRandonnees(List<Randonnee> mesRandonnees) {
        this.mesRandonnees = mesRandonnees;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mes_randonnees,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.place.setText(mesRandonnees.get(position).getPlace());
        Date date = mesRandonnees.get(position).getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String strDate = dateFormat.format(date);
        holder.date.setText(strDate);
    }

    @Override
    public int getItemCount() {
        return mesRandonnees.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView place,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);

        }
    }
}
