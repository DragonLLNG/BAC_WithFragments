package com.example.homework3;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BACCalculator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BACCalculator extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String weightOut;
    private Integer numDrinks;
    private double bacOut;

    public BACCalculator() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BACCalculator.
     */
    // TODO: Rename and change types and number of parameters
    public static BACCalculator newInstance(String param1, String param2) {
        BACCalculator fragment = new BACCalculator();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    /*
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
}
         */


    TextView bacTxt, status, numDrinksTxt, userWeight;
    static Profile user = new Profile();
    static ArrayList<Drink> drinksList = new ArrayList<Drink>();
    Button addDrinkBt, viewDrinkBt;


    public void updateBAC(Profile user, ArrayList<Drink> drinksList) {
        try {
            this.user = user;
            this.drinksList = drinksList;
            this.bacOut = bacOut;
            weightOut = this.user.getWeight() + " lbs" + "(" + this.user.getGender() + ")";
            numDrinks = this.drinksList.size();
        }
        catch (Resources.NotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View bacView = inflater.inflate(R.layout.fragmentbaccalculator, container, false);



        //Click Set button
        bacView.findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bac.gotoSetProfile();
            }
        });

        userWeight = bacView.findViewById(R.id.initial_weight);
        numDrinksTxt = bacView.findViewById(R.id.num_drink);
        bacTxt = bacView.findViewById(R.id.bac_level);
        addDrinkBt = bacView.findViewById(R.id.add_drink);
        viewDrinkBt = bacView.findViewById(R.id.view_drink);


        //Display user input weight and gender
        userWeight.setText(weightOut);


        //Disable Add Drink and View Drinks button when there's no weight and gender output
        if(userWeight.getText().length()==0){
            addDrinkBt.setEnabled(false);
            viewDrinkBt.setEnabled(false);
        }


        //Display number of added drinks
        numDrinksTxt.setText(String.valueOf(this.numDrinks));


        //BAC calculation
        double bacNum = 0.0;
        double consumed = 0.0;


        for (int i = 0; i < drinksList.size(); i++) {
            consumed += drinksList.get(i).getAlcPercent()*drinksList.get(i).getSize();
            System.out.println(consumed);
        }
        if (user.getGender()=="Male") {
            bacNum = (consumed * 5.14)/(user.getWeight() * 0.73);
            System.out.println(bacNum);
        } else {
            bacNum = (consumed * 5.14)/(user.getWeight() * 0.66);

            System.out.println(user.getWeight());
            System.out.println(bacNum);

        }


        //Changing the color of the status bar
        status = bacView.findViewById(R.id.status);
        status.setBackgroundResource(R.drawable.roundedcorner);
        GradientDrawable drawable = (GradientDrawable) status.getBackground();

        if (bacNum >= 0.25) {
            System.out.println(bacNum);
            //disable add drink
            addDrinkBt.setEnabled(false);
            bacView.findViewById(R.id.add_drink).setEnabled(false);
            status.setText("Over the limit");
            drawable.setColor(Color.RED);
            addDrinkBt.setEnabled(false);

            Toast overLimit = Toast.makeText(getActivity(), "No more drinks for you!", Toast.LENGTH_LONG);
            overLimit.setGravity(Gravity.CENTER, 0, 0);
            overLimit.show();
        }

        else if (bacNum > 0.2) {
            status.setText("Over the limit");
            drawable.setColor(Color.RED);

        } else if (bacNum > 0.08) {
            status.setText("Be Careful!");
            drawable.setColor(Color.YELLOW);
        } else {
            status.setText("You're Safe");
            drawable.setColor(Color.GREEN);
        }


        //Outputting the bac
        bacTxt.setText(String.format("%.3f", bacNum));




        //Click Add Drink button
        addDrinkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bac.gotoAddDrink();
            }
        });

        //Click View Drink
        viewDrinkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drinksList.size()==0){

                    Toast overLimit = Toast.makeText(getActivity(), "There is no drinks!", Toast.LENGTH_SHORT);
                    overLimit.setGravity(Gravity.CENTER, 0, 0);
                    overLimit.show();

                }
                bac.gotoViewDrink();
                bac.gotoViewDrink2(drinksList);
            }
        });

        //Click Reset button
        bacView.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    addDrinkBt.setEnabled(false);
                    viewDrinkBt.setEnabled(false);
                    status.setBackgroundResource(R.drawable.roundedcorner);
                    GradientDrawable drawable = (GradientDrawable) status.getBackground();
                    drinksList.clear();
                    bacTxt.setText("0.000");
                    userWeight.setText("N/A");
                    numDrinksTxt.setText("0");
                    status.setText("You're safe");
                    drawable.setColor(Color.GREEN);
                    user = new Profile();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return bacView;
    }


    //BAC interface
    BAC_interface bac;
    public interface BAC_interface{
        void gotoSetProfile();
        void gotoAddDrink();
        void gotoViewDrink();
        void gotoViewDrink2(ArrayList<Drink> drinkArrayList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BAC_interface) {
            bac = (BAC_interface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement BAC_interface");
        }
    }
}