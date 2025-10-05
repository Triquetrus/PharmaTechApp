package com.example.pharmatech;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    View view;
    EditText edt1,edt2,edt3,edt4,edt5,edt6;
    RadioButton m1,f1,o1;
    Button btn1;
    DatabaseReference db;
    public static String gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);


        edt1=view.findViewById(R.id.edt1);
        edt2=view.findViewById(R.id.edt2);
        edt3=view.findViewById(R.id.edt3);
        edt4=view.findViewById(R.id.edt4);
        edt5=view.findViewById(R.id.edt5);
        edt6=view.findViewById(R.id.edt6);
        btn1=view.findViewById(R.id.btn1);
        m1=view.findViewById(R.id.m1);
        f1=view.findViewById(R.id.f1);
        o1=view.findViewById(R.id.o1);
        db= FirebaseDatabase.getInstance().getReference().child("ProfileInfo");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edt1.getText().toString();
                String bio= edt2.getText().toString();
                String emailId = edt3.getText().toString();
                String mobile = edt4.getText().toString();
                String birthdate = edt5.getText().toString();
                String address = edt6.getText().toString();

                if (m1.isChecked()){
                     gender = "Male";
                }else if (f1.isChecked()){
                    gender= "Female";
                }else if(o1.isChecked()){
                    gender= "Other";
                }

                ProfileInfo u1 = new ProfileInfo(name,bio,emailId,mobile,birthdate,address,gender);
                db.child(name).setValue(u1);

                Toast.makeText(getContext(),"Account Info Saved!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}