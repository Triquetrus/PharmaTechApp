package com.example.pharmatech;


import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);
        replaceFragment(new HomeFragment());

        bottomNavigationView1=findViewById(R.id.bottomNavigationView1);

        bottomNavigationView1.setOnItemSelectedListener(item -> {

            int i=item.getItemId();
            if(i==R.id.home) {
                replaceFragment(new HomeFragment());
            }
            else if(i==R.id.profile) {
                replaceFragment(new ProfileFragment());
            }
            else if(i==R.id.settings) {
               replaceFragment(new SettingsFragment());
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout1, fragment);
        fragmentTransaction.commit();

    }
}