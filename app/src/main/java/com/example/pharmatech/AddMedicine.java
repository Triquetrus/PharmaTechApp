package com.example.pharmatech;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedicine extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4,edt5,edt6,edt7;
    Button btn1,btn2;
    DatabaseReference db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        edt5=findViewById(R.id.edt5);
        edt6=findViewById(R.id.edt6);
        edt7=findViewById(R.id.edt7);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        db= FirebaseDatabase.getInstance().getReference().child("MedicineInfo");

        progressDialog = new ProgressDialog(AddMedicine.this);
        progressDialog.setTitle("Adding Item");
        progressDialog.setMessage("We're adding the item to our database");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                String name = edt1.getText().toString();
                String detail= edt2.getText().toString();
                String id = edt3.getText().toString();
                String price = edt4.getText().toString();
                String expdate = edt5.getText().toString();
                String shelfno = edt6.getText().toString();
                String quantity = edt7.getText().toString();
                double Total = Double.parseDouble(price)*Double.parseDouble(quantity);
                String TotalPrice = Double.toString(Total);
                MedicineData s1 = new MedicineData(name,detail,id,price,expdate,shelfno,quantity,TotalPrice);
                db.child(name).setValue(s1);

                progressDialog.dismiss();
                Toast.makeText(AddMedicine.this,"Item Added Successfully!",Toast.LENGTH_SHORT).show();
                Toast.makeText(AddMedicine.this,"Total Price: " + TotalPrice,Toast.LENGTH_SHORT).show();
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                edt4.setText("");
                edt5.setText("");
                edt6.setText("");
                edt7.setText("");
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