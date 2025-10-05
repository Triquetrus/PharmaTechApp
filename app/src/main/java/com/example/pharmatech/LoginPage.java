package com.example.pharmatech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    EditText edt1, edt2,edt3;
    Button btn1, btn2, btn3;
    FirebaseAuth auth;
    public static DatabaseReference ref;
    public static  String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        auth = FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference().child("Users");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edt3.getText().toString();

                SharedPreferences sharedPref = LoginPage.this .getSharedPreferences("username", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", username);
                editor.apply();

                ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String role = snapshot.child("role").getValue(String.class);

                            if (role.equals("owner")) {

                                ownerlogin();

                            }

                            if (role.equals("customer")) {

                                customerlogin();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginPage.this, HomePage.class);
                startActivity(i);
            }
        });

    }

    private void ownerlogin() {

        if (edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty()) {
            Toast.makeText(LoginPage.this, "Please Enter Email/Password", Toast.LENGTH_SHORT).show();
        }

        auth.signInWithEmailAndPassword(edt1.getText().toString(), edt2.getText().toString()).
                addOnCompleteListener(task -> {



                    if (task.isSuccessful()) {
                        Intent i1 = new Intent(LoginPage.this, HomePage.class);
                        startActivity(i1);
                    } else {
                        Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

    }


    private void customerlogin() {

        if (edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty()) {
            Toast.makeText(LoginPage.this, "Please Enter Email/Password", Toast.LENGTH_SHORT).show();
        }

        auth.signInWithEmailAndPassword(edt1.getText().toString(), edt2.getText().toString()).
                addOnCompleteListener(task -> {


                    if (task.isSuccessful()) {
                        Intent i1 = new Intent(LoginPage.this, CustomerHome.class);
                        i1.putExtra("Name", username);
                        startActivity(i1);
                    } else {
                        Toast.makeText(LoginPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
    }
}