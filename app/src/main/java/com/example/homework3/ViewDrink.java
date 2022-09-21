package com.example.homework3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
public class ViewDrink extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DRINKLIST = "param_drinkList";

    // TODO: Rename and change types of parameters
    private TextView label, size, percent, dateAdded;
    private int iterator = 0;
    private ArrayList<Drink> drinkList = new ArrayList<Drink>();
    private Drink drinkRemove;

    public ViewDrink() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ViewDrink newInstance(ArrayList<Drink> list) {
        ViewDrink fragment = new ViewDrink();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_DRINKLIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            drinkList = getArguments().getParcelableArrayList(ARG_DRINKLIST);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewDrink = inflater.inflate(R.layout.fragmentviewdrink, container, false);
        return viewDrink;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        label = view.findViewById(R.id.num_drinks_view);
        size = view.findViewById(R.id.drink_size_view);
        percent = view.findViewById(R.id.percent_view);

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

        view.findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {


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

        view.findViewById(R.id.previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
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


        view.findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                drinkRemove = drinkList.get(iterator);
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
                    vd.deletedDrink(drinkRemove);
                }
            }
        });



        view.findViewById(R.id.close_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vd.updatedDrinklists(drinkList);
            }
        });



    }

    ViewDrinksInterface vd;

    public interface ViewDrinksInterface {
        void deletedDrink(Drink drinkDeleted);
        void updatedDrinklists(ArrayList<Drink> updatedDrinksList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ViewDrinksInterface) {
            vd = (ViewDrinksInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement ViewDrinksInterface");
        }
    }

}