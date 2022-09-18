package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BACCalculator.BAC_interface, SetProfile.WeightGenderInterface, AddDrink.AddDrinkInterface {



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
    Drink drinkAdded;
    ArrayList<Drink> drinksList = new ArrayList<Drink>();


    @Override
    public void goBacktoBAC() {

       getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new BACCalculator(),"BACFragment")
               .addToBackStack(null).commitNow();

    }

    @Override
    public void setWeighGender(Profile profile) {
        user = profile;
        BACCalculator bacFragment = (BACCalculator) getSupportFragmentManager().findFragmentByTag("BACFragment");
        bacFragment.updateBAC(user,new ArrayList<Drink>());

    }

    @Override
    public void goBacktoBAC1() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new BACCalculator(),"BACFragment")
                .commitNow();
    }

    @Override
    public void setDrink(Drink drink) {
        drinkAdded = drink;
        drinksList.add(drinkAdded);
        BACCalculator bacFragment1 = (BACCalculator) getSupportFragmentManager().findFragmentByTag("BACFragment");
        bacFragment1.updateBAC(user,drinksList);

    }


    @Override
    public void gotoViewDrink() {
        setTitle("View Drinks");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,new ViewDrink(),"ViewDrinkFragment")
                .commitNow();

    }

    @Override
    public void gotoViewDrink2(ArrayList<Drink> drinkData) {
        drinksList = drinkData;
        ViewDrink viewDrinkFragment = (ViewDrink) getSupportFragmentManager().findFragmentByTag("ViewDrinkFragment");
        viewDrinkFragment.updateDrinkList(drinksList);
    }
}