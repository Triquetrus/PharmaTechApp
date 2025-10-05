package com.example.pharmatech;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class CustomerRecords extends AppCompatActivity {

    EditText edt1,edt2;
    TextView textView2;
    Button btn1,btn2;
    DatabaseReference db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_records);


        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        textView2=findViewById(R.id.textView2);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        ref= FirebaseDatabase.getInstance().getReference().child("BillInfo");
        db = FirebaseDatabase.getInstance().getReference().child("CustomerInfo");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMedicine();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void searchMedicine() {

        String customer = edt1.getText().toString();
        String doctorName  = edt2.getText().toString();


        if (customer.isEmpty() || doctorName.isEmpty()) {
            Toast.makeText(CustomerRecords.this, "Please enter the details!", Toast.LENGTH_SHORT).show();
            return;
        }

        ref.child(customer).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String customer = snapshot.child("customer").getValue(String.class);
                    String description = snapshot.child("medicineDetail").getValue(String.class);
                    String storeName = snapshot.child("storeName").getValue(String.class);


                    String result = "Name: " + customer + "\n" +
                            "Doctor: " + doctorName + "\n" +
                            "Item Bought: " + description + "\n" +
                            "Store: " + storeName;

                    CustomerData c1 = new CustomerData(customer,doctorName,storeName,description);
                    db.child(Objects.requireNonNull(customer)).setValue(c1);

                    textView2.setText(result);
                } else {
                    textView2.setText("No customer found with the name " + customer);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerRecords.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}