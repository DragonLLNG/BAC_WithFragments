package com.example.homework3;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile{
    double weight;
    String gender;

    public Profile(){
    }

    public Profile(double weight, String gender){
        this.weight = weight;
        this.gender = gender;
    }


    public double getWeight(){
        return weight;
    }
    public String getGender(){
        return gender;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

}
