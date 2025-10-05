package com.example.pharmatech;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPage extends AppCompatActivity {


    EditText edt1, edt2, edt3,edt4;
    Button btn1, btn2;
    RadioButton m1,f1;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    public static String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup_page);


        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        m1= findViewById(R.id.m1);
        f1= findViewById(R.id.f1);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignupPage.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");


        btn1.setOnClickListener(view -> {

            progressDialog.show();
            String username = edt1.getText().toString();
            if(username.isEmpty()) {
                Toast.makeText(SignupPage.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
            }

            SharedPreferences sharedPref = SignupPage.this .getSharedPreferences("username",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", username);
            editor.apply();

                String number = edt2.getText().toString();
            if(number.isEmpty()) {
                Toast.makeText(SignupPage.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
            }
                String email = edt3.getText().toString();
            if(email.isEmpty()) {
                Toast.makeText(SignupPage.this, "Please Enter Email ID", Toast.LENGTH_SHORT).show();
            }
                String password = edt4.getText().toString();
            if(password.isEmpty()) {
                Toast.makeText(SignupPage.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }

                auth.createUserWithEmailAndPassword
                                (edt3.getText().toString(), edt4.getText().toString()).
                        addOnCompleteListener(task -> {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                if(m1.isChecked()) {
                                    role = "owner";

                                    Toast.makeText(SignupPage.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                                    Intent i1 = new Intent(SignupPage.this, Onboarding1.class);
                                    startActivity(i1);

}
                                 if(f1.isChecked())
                                {
                                    Toast.makeText(SignupPage.this, "User Created Successfully", Toast.LENGTH_SHORT).show();

                                    Intent i1 = new Intent(SignupPage.this, CustomerHome.class);
                                    i1.putExtra("Name",username);
                                    startActivity(i1);
                                    role = "customer";
                                }

                                String id = task.getResult().getUser().getUid();
                                SignupInfo user = new SignupInfo(username, email, password, number, id,role);

                                database.getReference().child("Users").child(username).setValue(user);
                            } else {
                                Toast.makeText(SignupPage.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        });

        });

        btn2.setOnClickListener(view -> {

            Intent i2=new Intent(SignupPage.this,LoginPage.class);
            startActivity(i2);
        });

    }

}