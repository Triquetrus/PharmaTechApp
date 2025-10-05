package com.example.pharmatech;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class BillPage extends AppCompatActivity {

    DatabaseReference ref;
    DatabaseReference db;
    EditText edt1, edt2, edt3, edt4;
    Button btn1, btn2,btn3;
    TextView textView2;
    ArrayList<String> medicineList = new ArrayList<>();
    double totalAmount = 0.0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_page);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        textView2 = findViewById(R.id.textView2);
        ref = FirebaseDatabase.getInstance().getReference().child("MedicineInfo");
        db= FirebaseDatabase.getInstance().getReference().child("BillInfo");

        progressDialog = new ProgressDialog(BillPage.this);
        progressDialog.setTitle("Generating Bill");
        progressDialog.setMessage("We're calculating your Bill!");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicine();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                generateBill();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void generateBill() {


        String storeName = edt1.getText().toString().trim();
        String customer = edt4.getText().toString().trim();
        Random r=new Random();
        int no=r.nextInt(1000);
        String billId=" "+no;

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        StringBuilder bill = new StringBuilder();
        bill.append("ID: ").append(billId).append("\n");
        bill.append("Store: ").append(storeName).append("\n");
        bill.append("Customer: ").append(customer).append("\n");
        bill.append("Date: ").append(currentDate).append("\n");
        bill.append("Time: ").append(currentTime).append("\n");
        bill.append("-------------------------------------------\n\n");
        bill.append("Items:\n");

        int i=1;
        for (String medicineDetail : medicineList) {
            bill.append("...........................................\n");
            bill.append(" ").append(i).append(".");
            bill.append(medicineDetail).append("\n");
            i++;
        }

        bill.append("\nTotal Amount: Rs.").append(String.format(Locale.getDefault(), "%.2f", totalAmount));

        String medicineDetail = String.valueOf(medicineList);
        BillData b1 = new BillData(billId,storeName,customer,currentDate,currentTime,medicineDetail,totalAmount);
        db.child(customer).setValue(b1);

        progressDialog.dismiss();

        textView2.setText(bill.toString());
        Toast.makeText(BillPage.this,"Bill Generated SuccessFully",Toast.LENGTH_SHORT).show();
        medicineList.clear();  // Clear the list after generating the bill
        totalAmount = 0;
    }


    private void addMedicine() {

        String storeName = edt1.getText().toString().trim();
        String medicineName = edt2.getText().toString().trim();
        double medicineQuantity = Double.parseDouble(edt3.getText().toString().trim());
        String customer = edt4.getText().toString().trim();

        if ( medicineName.isEmpty() || medicineQuantity==0.0) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        ref.child(medicineName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                String medicine = Objects.requireNonNull(task.getResult().child("name").getValue()).toString();
                if (medicineName.equals(medicine)) {
                    String priceStr = Objects.requireNonNull(task.getResult().child("price").getValue()).toString();
                    double price=Double.parseDouble(priceStr);
                    double totalPrice = medicineQuantity * price;

                    String medicineDetail = "Item: " + medicineName + "\n" +
                            "Quantity: " + medicineQuantity + "\n" +
                            "Price: Rs." + price + "\n" +
                            "Total: Rs." + totalPrice;
                    medicineList.add(medicineDetail);
                    totalAmount += totalPrice;

                    Toast.makeText(BillPage.this, "Item added successfully!", Toast.LENGTH_SHORT).show();
                    clearMedicineFields();
                } else {
                    Toast.makeText(BillPage.this, "Item not found in database!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void clearMedicineFields() {

        edt2.setText("");
        edt3.setText("");
    }
}
