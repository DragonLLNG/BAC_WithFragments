package com.example.homework3;

public class Drink {

    private double alcPercent;
    private int size;


    public Drink(double percent, int size){
        this.alcPercent = percent;
        this.size = size;
    }

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




}
