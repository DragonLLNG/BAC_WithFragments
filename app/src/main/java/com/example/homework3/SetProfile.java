package com.example.homework3;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetProfile extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static SetProfile newInstance(String param1, String param2) {
        SetProfile fragment = new SetProfile();
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

    Profile userInfo;
    double num_weight;
    EditText weight_input;
    RadioGroup gender;
    String genderReturn;
    Button setw, cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View profileView =  inflater.inflate(R.layout.fragmentsetprofile, container, false);

        setw =profileView.findViewById(R.id.set2);
        setw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    weight_input = profileView.findViewById(R.id.weight_input);
                    num_weight = Double.parseDouble(weight_input.getText().toString());
                } catch(NumberFormatException exception){
                    System.out.println("Weight is not format corrected");
                }

                gender = profileView.findViewById(R.id.gender_group);
                int checked = gender.getCheckedRadioButtonId();
                if(checked==R.id.female){
                    genderReturn="Female";
                }
                else if (checked==R.id.male){
                    genderReturn="Male";
                }
                if(weight_input.getText().length()==0 || gender.getCheckedRadioButtonId() == -1){
                    Toast toast = Toast.makeText(getActivity(), "Set weight and gender first.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else{
                    userInfo = new Profile(num_weight,genderReturn);
                    wg.setWeighGender(userInfo);


                }
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        //Click Cancel button
        cancel = profileView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return profileView;
    }

    WeightGenderInterface wg;
    public interface WeightGenderInterface{
        void goBacktoBAC();
        void setWeighGender(Profile profile);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SetProfile.WeightGenderInterface) {
            wg = (SetProfile.WeightGenderInterface) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement WeightGenderInterface");
        }
    }
}