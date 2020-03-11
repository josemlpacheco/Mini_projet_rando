package com.project.draggerlogin.ui.home;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Randonnee {
    @SerializedName("date")
    private Date date;
    @SerializedName("place")
    private String place;
    @SerializedName("nb_part_min")
    private int nb_part_min;
    @SerializedName("nb_part_max")
    private int nb_part_max;
    @SerializedName("nb_part_miss")
    private int nb_part_miss;
    @SerializedName("date_limit")
    private Date date_limit;

    public Randonnee(Date date, String place, int nb_part_min, int nb_part_max, int nb_part_miss, Date date_limit) {
        this.date = date;
        this.place = place;
        this.nb_part_min = nb_part_min;
        this.nb_part_max = nb_part_max;
        this.nb_part_miss = nb_part_miss;
        this.date_limit = date_limit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNb_part_min() {
        return nb_part_min;
    }

    public void setNb_part_min(int nb_part_min) {
        this.nb_part_min = nb_part_min;
    }

    public int getNb_part_max() {
        return nb_part_max;
    }

    public void setNb_part_max(int nb_part_max) {
        this.nb_part_max = nb_part_max;
    }

    public int getNb_part_miss() {
        return nb_part_miss;
    }

    public void setNb_part_miss(int nb_part_miss) {
        this.nb_part_miss = nb_part_miss;
    }

    public Date getDate_limit() {
        return date_limit;
    }

    public void setDate_limit(Date date_limit) {
        this.date_limit = date_limit;
    }
}
