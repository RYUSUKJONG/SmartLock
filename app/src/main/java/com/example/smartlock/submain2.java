package com.example.smartlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class submain2 extends AppCompatActivity {

    private static final int CALL_FOR_CREATION = 0;

    private Button sendbt1;
    private Button sendbt2;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain2);

        sendbt1 = (Button) findViewById(R.id.button6);
        sendbt2 = (Button) findViewById(R.id.button7);

        sendbt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("led").setValue("1");    //led 켜기
            }
        });

        sendbt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("led").setValue("0");    //led 끄기
            }
        });
    }

    public void sub2Button1(View view) {
        Intent intent = new Intent(this, sub2_camera.class);
        startActivityForResult(intent, CALL_FOR_CREATION);
    }

    public void sub2Button2(View view) {
    }

    public void sub2Button3(View view) {
    }

    public void sub2Button4(View view) {
        finish();
    }
}
