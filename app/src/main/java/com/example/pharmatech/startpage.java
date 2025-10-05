package com.example.pharmatech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startpage extends AppCompatActivity {

    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_startpage);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

            btn1.setOnClickListener(view -> {

                Intent i1 = new Intent(startpage.this, LoginPage.class);
                startActivity(i1);
                finish();
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i2 = new Intent(startpage.this, SignupPage.class);
                    startActivity(i2);
                    finish();
                }
            });
        }
    }