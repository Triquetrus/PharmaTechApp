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

public class AboutMedicine extends AppCompatActivity {

    EditText edt1;
    TextView textView2;
    Button btn1,btn2;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_about_medicine);

        edt1=findViewById(R.id.edt1);
        textView2=findViewById(R.id.textView2);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);



        ref= FirebaseDatabase.getInstance().getReference().child("MedicineInfo");

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

        String medicineName = edt1.getText().toString();

        if (medicineName.isEmpty()) {
            Toast.makeText(AboutMedicine.this, "Please enter a Item name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        ref.child(medicineName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String description = snapshot.child("detail").getValue(String.class);
                    String quantity = snapshot.child("quantity").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String shelf = snapshot.child("shelfno").getValue(String.class);

                    String result = "Name: " + name + "\n" +
                            "Description: " + description + "\n" +
                            "Price: " + price + "\n" +
                            "Quantity: " + quantity + "\n" +
                            "Shelf No.: " +shelf;

                    textView2.setText(result);
                } else {
                    textView2.setText("No Item found with the name " + medicineName);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AboutMedicine.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}