package com.example.pharmatech;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackPage extends AppCompatActivity {

    EditText edt1,edt2;
    Button btn1,btn2;
    RatingBar rating;
    DatabaseReference db;
    public static String mobile = "7843040936";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        rating=findViewById(R.id.rating);
        db= FirebaseDatabase.getInstance().getReference().child("Feedback");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feedback = edt1.getText().toString();
                String suggestion = edt2.getText().toString();
                String RATING = " " + rating.getRating();
                String d = "FeedBack:" + feedback + ",Suggestions:" + suggestion + ": Rating:" + RATING;
                String msg = "Hello,Thank You For Your Feedback On PharmaTech";

                Toast.makeText(FeedbackPage.this, d, Toast.LENGTH_SHORT).show();
                StoreFeedback s1 = new StoreFeedback(feedback, suggestion, RATING);
                db.child(feedback).setValue(s1);

                Intent i = new Intent();
                PendingIntent pi = PendingIntent.getActivity(FeedbackPage.this, 0, i, PendingIntent.FLAG_IMMUTABLE);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mobile, null, msg, pi, null);

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