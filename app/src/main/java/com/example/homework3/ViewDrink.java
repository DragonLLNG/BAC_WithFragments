package com.example.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrink#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrink extends Fragment implements SetProfile.WeightGenderInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewDrink() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDrink.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrink newInstance(String param1, String param2) {
        ViewDrink fragment = new ViewDrink();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    private TextView label, size, percent, dateAdded;
    private int iterator = 0;
    private ArrayList<Drink> drinkList = new ArrayList<Drink>();


    public void updateDrinkList(ArrayList<Drink> drinkList){
        this.drinkList = drinkList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewDrink = inflater.inflate(R.layout.fragmentviewdrink, container, false);

        label = viewDrink.findViewById(R.id.num_drinks_view);
        size = viewDrink.findViewById(R.id.drink_size_view);
        percent = viewDrink.findViewById(R.id.percent_view);


        if(drinkList.size() != 0){

            //Label
            String sizeStr = Integer.toString(drinkList.size());
            String itrString = Integer.toString(iterator+1);
            label.setText("Drink " + itrString + " out of " + sizeStr);

            //Ounces
            String drinkOz = Integer.toString(drinkList.get(iterator).getSize());
            size.setText(drinkOz + " oz");

            //Percent
            String percentStr = Double.toString(drinkList.get(iterator).getAlcPercent()*100);
            percent.setText(percentStr + "% Alcohol");
        }


        viewDrink.findViewById(R.id.close_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vd.goBacktoBAC();

            }
        });

        viewDrink.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterator++;
                if( iterator > drinkList.size() -1){
                    iterator = drinkList.size() - 1;
                }
                //Label
                String sizeStr = Integer.toString(drinkList.size());
                String itrString = Integer.toString(iterator+1);
                label.setText("Drink " + itrString + " out of " + sizeStr);

                //Ounces
                String drinkOz = Integer.toString(drinkList.get(iterator).getSize());
                size.setText(drinkOz + " oz");

                //Percent
                String percentStr = Double.toString(drinkList.get(iterator).getAlcPercent()*100);
                percent.setText(percentStr + "% Alcohol");
                System.out.println("you hit prev");
            }
        });

        viewDrink.findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iterator--;
                if( iterator < 0){
                    iterator = drinkList.size() - 1;
                }
                //Label
                String sizeStr = Integer.toString(drinkList.size());
                String itrString = Integer.toString(iterator+1);
                label.setText("Drink " + itrString + " out of " + sizeStr);

                //Ounces
                String drinkOz = Integer.toString(drinkList.get(iterator).getSize());
                size.setText(drinkOz + " oz");

                //Percent
                String percentStr = Double.toString(drinkList.get(iterator).getAlcPercent()*100);
                percent.setText(percentStr + "% Alcohol");
                System.out.println("you hit prev");
            }
        });


        viewDrink.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkList.remove(iterator);
                iterator -= 1;
                if(drinkList.size() != 0){
                    if(iterator < 0){
                        iterator = 0;
                        //Label
                        String sizeStr = Integer.toString(drinkList.size());
                        String itrString = Integer.toString(iterator+1);
                        label.setText("Drink " + itrString + " out of " + sizeStr);

                        //Ounces
                        String drinkOz = Integer.toString(drinkList.get(iterator).getSize());
                        size.setText(drinkOz + " oz");

                        //Percent
                        String percentStr = Double.toString(drinkList.get(iterator).getAlcPercent()*100);
                        percent.setText(percentStr + "% Alcohol");
                        System.out.println("you hit prev");
                    }

                }
                else{
                    label.setText("Drink # out of N");
                    size.setText("1 oz");
                    percent.setText("x% Alcohol");
                }
            }
        });

        return viewDrink;
    }

    SetProfile.WeightGenderInterface vd;
    @Override
    public void goBacktoBAC() {

    }

    @Override
    public void setWeighGender(Profile profile) {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SetProfile.WeightGenderInterface) {
            vd = (SetProfile.WeightGenderInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement WeightGenderInterface");
        }
    }
}