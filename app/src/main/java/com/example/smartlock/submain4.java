package com.example.smartlock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class submain4 extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    public String msg1;
    public String msg2;
    FirebaseDatabase database;
    DatabaseReference myRef;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain4);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText3);
        btn1 = (Button) findViewById((R.id.button12));


        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("password");

        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg1 = et1.getText().toString();
                msg2 = et2.getText().toString();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if(value.equals(msg1)){
                            Toast.makeText(submain4.this, "기존 비밀번호와 일치합니다.", Toast.LENGTH_SHORT).show();
                            //msg2 = et2.getText().toString();
                            databaseReference.child("password").setValue(msg2);
                            Toast.makeText(submain4.this, "비밀번호가 변경되었습니다.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else if(!value.equals(msg1)){
                            Toast.makeText(submain4.this, "기존 비밀번호와 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                //msg2 = et2.getText().toString();
                //databaseReference.child("password").setValue(msg2);
            }
        });


/*
        sendbt = (Button) findViewById(R.id.button12);

        sendbt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("message").push().setValue("2");
            }
        });
*/
    }

    public void sub4Button2(View view) {
        finish();
    }
}
