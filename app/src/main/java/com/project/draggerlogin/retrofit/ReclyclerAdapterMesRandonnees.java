package com.project.draggerlogin.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.draggerlogin.R;
import com.project.draggerlogin.login.User;
import com.project.draggerlogin.ui.home.Randonnee;

import java.util.List;

public class ReclyclerAdapterMesRandonnees extends RecyclerView.Adapter<ReclyclerAdapterMesRandonnees.MyViewHolder> {
    private List<Randonnee> mesRandonnees;

    public ReclyclerAdapterMesRandonnees(List<Randonnee> mesRandonnees) {
        this.mesRandonnees = mesRandonnees;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.place.setText(mesRandonnees.get(position).getPlace());
        holder.date.setText((CharSequence) mesRandonnees.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mesRandonnees.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView place;
        EditText date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);

        }
    }
}
