package com.example.smartlock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class sub1_lock extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView tvMessage;
    EditText etNewMessage;
    Button btUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1_lock);

        tvMessage = (TextView) findViewById(R.id.textView4);
        //btUpdate = (Button) findViewById(R.id.bt_update);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("lockstate");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("0")) {
                    tvMessage.setText("현재 닫혀있습니다.");
                    tvMessage.setBackground(getResources().getDrawable(R.drawable.green_circle));

                }
                else if (value.equals("1")) {
                    tvMessage.setText("현재 열려있습니다.");
                    tvMessage.setBackground(getResources().getDrawable(R.drawable.red_circle));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }



    public void sub1Button1(View view) {
        finish();
    }
}
