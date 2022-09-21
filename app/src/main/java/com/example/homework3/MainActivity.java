package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BACCalculator.BAC_interface, SetProfile.sendWeightGenderInterface, AddDrink.AddDrinkInterface, ViewDrink.ViewDrinksInterface {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("BAC Calculator");
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new BACCalculator(),"BACFragment")
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void gotoSetProfile() {
        setTitle("Set Weigh/Gender");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,new SetProfile(),"ProfileFragment").addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoAddDrink() {
        setTitle("Add Drink");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,new AddDrink(),"AddDrinkFragment").addToBackStack(null)
                .commit();
    }



    Profile user;
    ArrayList<Drink> drinksList = new ArrayList<>();

    @Override
    public void setWeighGender(Profile profile) {

        user = profile;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, BACCalculator.newInstance(user,drinksList),"BACFragment")
                .commit();
        //getSupportFragmentManager().popBackStack();

    }

    @Override
    public void setDrink(Drink drink) {
        drinksList.add(drink);
        //BACCalculator bacFragment1 = (BACCalculator) getSupportFragmentManager().findFragmentByTag("BACFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, BACCalculator.newInstance(user, drinksList),"BACFragment")
                .commit();
        //getSupportFragmentManager().popBackStack();

    }




    @Override
    public void gotoViewDrink(ArrayList<Drink> drinkData) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, ViewDrink.newInstance(drinksList),"ViewDrinkFragment")
                .commit();
    }


    @Override
    public void deletedDrink(Drink drinkDeleted) {
        drinksList.remove(drinkDeleted);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, BACCalculator.newInstance(user,drinksList),"BACFragment")
                .commit();


    }

    @Override
    public void updatedDrinklists(ArrayList<Drink> updatedDrinksList) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, BACCalculator.newInstance(user, updatedDrinksList),"BACFragment")
                .commit();
    }


}