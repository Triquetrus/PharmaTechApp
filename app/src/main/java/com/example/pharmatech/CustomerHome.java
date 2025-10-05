package com.example.pharmatech;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class CustomerHome extends AppCompatActivity {

    FirebaseAuth auth;
    TextView textView2;
    CardView cardView1,cardView2,cardView3;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        AppCompatActivity activity = CustomerHome.this;
        Objects.requireNonNull(activity).setSupportActionBar(toolbar);


        textView2=findViewById(R.id.textView2);
        cardView1=findViewById(R.id.cardView1);
        cardView2=findViewById(R.id.cardView2);
        cardView3=findViewById(R.id.cardView3);


        String Name = getIntent().getStringExtra("Name");
        textView2.setText(Name);


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(CustomerHome.this,MedicineList.class);
                startActivity(i1);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(CustomerHome.this,AboutMedicine.class);
                startActivity(i2);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater m=getMenuInflater();
        m.inflate(R.menu.customer_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int i=item.getItemId();
        if(i==R.id.review_page)
        {
            Intent i1 = new Intent(CustomerHome.this,AboutUs.class);
            startActivity(i1);
        }
        else if(i==R.id.feedback_page)
        {
            Intent i1 = new Intent(CustomerHome.this,FeedbackPage.class);
            startActivity(i1);
        }
        else if(i==R.id.logout)
        {
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent i1 = new Intent(CustomerHome.this,startpage.class);
            startActivity(i1);
        }

        return super.onOptionsItemSelected(item);
    }
}