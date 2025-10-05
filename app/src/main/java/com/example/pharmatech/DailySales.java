package com.example.pharmatech;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DailySales extends AppCompatActivity {

    Button btn2;
    ListView bills;
    DatabaseReference ref;
    BillData db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sales);

        btn2=findViewById(R.id.btn2);
        bills=findViewById(R.id.bills);
        ArrayList<String> data=new ArrayList<String>();
        ArrayAdapter<String> ad=new ArrayAdapter<String>(DailySales.this,
                android.R.layout.simple_spinner_item
                ,data );

        ref= FirebaseDatabase.getInstance().getReference().child("BillInfo");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=1;
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    db=ds.getValue(BillData.class);
                    data.add("Bill Id:"+ db.getBillId());
                    data.add("Customer: " + db.getCustomer());
                    data.add("Total Amount: "+ db.getTotalAmount());
                    data.add("Time: "+ db.getCurrentTime());

                    i++;
                }
                bills.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}