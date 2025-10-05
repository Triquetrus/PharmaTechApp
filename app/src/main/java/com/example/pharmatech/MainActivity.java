package com.example.pharmatech;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView lottie2;
    FirebaseAuth auth;
    public static DatabaseReference ref;
    public String role;
    public static String userId;
    SignupInfo s1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        auth = FirebaseAuth.getInstance();

        userId = auth.getUid();
        lottie2=findViewById(R.id.lottie2);

        new Handler().postDelayed(() -> {

        userId = auth.getUid();

        if(auth.getCurrentUser() == null){

            Intent i1 = new Intent(MainActivity.this, startpage.class);
            startActivity(i1);
        }

        if (auth.getCurrentUser() != null) {

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        s1 = ds.getValue(SignupInfo.class);
                        if (s1.getId().equals(userId)) {
                            role = s1.getRole();
                            if (role.equals("customer")) {
                                if (auth.getCurrentUser() != null) {

                                    Intent i2 = new Intent(MainActivity.this, CustomerHome.class);
                                    startActivity(i2);
                                }
                            } else {
                                if (auth.getCurrentUser() != null) {
                                    Intent i3 = new Intent(MainActivity.this, HomePage.class);
                                    startActivity(i3);
                                }
                            }
                        }
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        },3500);
    }

}
