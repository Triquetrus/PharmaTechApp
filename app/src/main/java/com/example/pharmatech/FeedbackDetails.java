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

public class FeedbackDetails extends AppCompatActivity {

    ListView list1;
    DatabaseReference ref;
    StoreFeedback db;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);


        btn2=findViewById(R.id.btn2);
        list1=findViewById(R.id.list1);
        ArrayList<String> data=new ArrayList<String>();
        ArrayAdapter<String> ad=new ArrayAdapter<String>(FeedbackDetails.this,
                android.R.layout.simple_spinner_item
                ,data );

        ref= FirebaseDatabase.getInstance().getReference().child("Feedback");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int i=1;
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    db=ds.getValue(StoreFeedback.class);
                    data.add("Feedback: " + db.getFeedback());
                    data.add("Suggestions: " + db.getSuggestion());
                    data.add("Rating: " + db.getRATING());
                    i++;
                }
                list1.setAdapter(ad);
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