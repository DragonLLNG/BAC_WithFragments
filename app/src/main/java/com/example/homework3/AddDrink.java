package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddDrink extends Fragment {

    public AddDrink() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    RadioGroup drinkSize;
    SeekBar alcohol;
    double alcoholPercent;
    TextView percentStr;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View addDrinkView =  inflater.inflate(R.layout.fragmentadddrink, container, false);

        return addDrinkView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Choose alcohol percent from seekbar
        alcohol = view.findViewById(R.id.alcohol_seekbar);
        alcohol.setMax(30);
        percentStr = view.findViewById(R.id.percent_txt);
        alcohol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentStr.setText(i+"%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Choose drink size
        drinkSize = view.findViewById(R.id.size_group);
        view.findViewById(R.id.addDrink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                System.out.println("You pressed Add Drink");

                //add drink goes here
                if (drinkSize.getCheckedRadioButtonId() == -1) {
                    Toast toast = Toast.makeText(getActivity(), "Please select a valid drink size", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }

                alcoholPercent = (double) alcohol.getProgress();
                System.out.println(alcoholPercent/100);

                int numDrink = drinkSize.getCheckedRadioButtonId();
                int ounces = 0;
                if(numDrink == R.id.one_oz){
                    ounces = 1;
                }
                else if(numDrink == R.id.five_oz){
                    ounces = 5;
                }
                else{
                    ounces = 12;
                }
                double decimal = alcoholPercent/100;
                Drink newDrink = new Drink(decimal, ounces);
                dr.setDrink(newDrink);
                getActivity().getSupportFragmentManager().popBackStack();
            }

        });





        //Click Cancel button
        view.findViewById(R.id.cancel2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    AddDrink.AddDrinkInterface dr;
    public interface AddDrinkInterface{
        void setDrink(Drink drink);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddDrink.AddDrinkInterface) {
            dr = (AddDrink.AddDrinkInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement AddDrinkInterface");
        }
    }
}