package com.example.pharmatech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.appcheck.interop.BuildConfig;

import java.util.Objects;

public class HomeFragment extends Fragment implements OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }
    View view;
    TextView textView2;
    CardView cardView1,CardView2,cardView3,CardView4,cardView5,CardView6;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = view.findViewById(R.id.navigationView);
        toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Objects.requireNonNull(activity).setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar ,R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        textView2=view.findViewById(R.id.textView2);
        cardView1=view.findViewById(R.id.cardView1);
        CardView2=view.findViewById(R.id.CardView2);
        cardView3=view.findViewById(R.id.cardView3);
        CardView4=view.findViewById(R.id.CardView4);
        cardView5=view.findViewById(R.id.cardView5);
        CardView6=view.findViewById(R.id.CardView6);


        cardView1.setOnClickListener(this);
        CardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        CardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
        CardView6.setOnClickListener(this);


        SharedPreferences sharedPref = view.getContext().getSharedPreferences("username",Context.MODE_PRIVATE);
        String username = sharedPref.getString( "username" ," ");
        textView2.setText(username);

        displayUsername();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_user_info) {
                    Toast.makeText(getContext(), "User Info Clicked", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_app_version) {
                    String versionName = BuildConfig.VERSION_NAME;
                    Toast.makeText(getContext(), "App Version: " + versionName, Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_check_update) {
                    Toast.makeText(getContext(), "Checking for updates...", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_share_app) {
                    shareApp();
                } else if (id == R.id.feedback_page) {
                    Intent i8 = new Intent(getContext(), FeedbackDetails.class);
                    startActivity(i8);
                }

                return true;
            }
        });

        
        return view;
    }

    @Override
    public void onClick(View view) {

        int v = view.getId();
        if(v==R.id.cardView1){
           Intent i1=new Intent(getContext(),AddMedicine.class);
           startActivity(i1);
        }else if(v==R.id.CardView2){
            Intent i2=new Intent(getContext(),AboutMedicine.class);
            startActivity(i2);
        }else if(v==R.id.cardView3){
            Intent i3=new Intent(getContext(),BillPage.class);
            startActivity(i3);
        }else if(v==R.id.CardView4){
            Intent i4=new Intent(getContext(),MedicineList.class);
            startActivity(i4);
        }else if(v==R.id.cardView5){
            Intent i5=new Intent(getContext(),DailySales.class);
            startActivity(i5);
        }else if(v==R.id.CardView6){
            Intent i6=new Intent(getContext(),CustomerRecords.class);
            startActivity(i6);
        }

    }

    private void displayUsername() {
        // Retrieve username from SharedPreferences and display
        String username = requireContext().getSharedPreferences("username", Context.MODE_PRIVATE)
                .getString("username", "");
        textView2.setText(username);
    }

    private void shareApp() {
        // Share app functionality
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Download the app from: [https://drive.google.com/drive/home]");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

}