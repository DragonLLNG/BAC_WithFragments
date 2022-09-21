package com.example.homework3;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Drink implements Parcelable {

    private double alcPercent;
    private int size;


    public Drink(double percent, int size){
        this.alcPercent = percent;
        this.size = size;
    }

    protected Drink(Parcel in) {
        alcPercent = in.readDouble();
        size = in.readInt();
    }

    public static final Creator<Drink> CREATOR = new Creator<Drink>() {
        @Override
        public Drink createFromParcel(Parcel in) {
            return new Drink(in);
        }

        @Override
        public Drink[] newArray(int size) {
            return new Drink[size];
        }
    };

    //Getters
    public double getAlcPercent(){
        return this.alcPercent;
    }
    public int getSize(){
        return this.size;
    }
    //Setters
    public void setAlcPercent(double percent){
        this.alcPercent = percent;
    }

    public void setSize(int size){
        this.size = size;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(alcPercent);
        dest.writeInt(size);
    }
}
