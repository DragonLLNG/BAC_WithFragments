package com.example.homework3;

import android.os.Parcel;
import android.os.Parcelable;


import java.util.Date;
public class Drink implements Parcelable {

    private double alcPercent;
    private int size;
    private Date date;


    public Drink(double percent, int size, Date date){
        this.alcPercent = percent;
        this.size = size;
        this.date = date;
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
    public Date getDate() {return this.date; }
    //Setters
    public void setAlcPercent(double percent){
        this.alcPercent = percent;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setDate(Date date) {this.date = date; }


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
