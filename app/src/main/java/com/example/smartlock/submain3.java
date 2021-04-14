package com.example.smartlock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class submain3 extends AppCompatActivity{

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain3);

        tvMessage = (TextView) findViewById(R.id.textView2);
        //btUpdate = (Button) findViewById(R.id.bt_update);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tilt");



        myRef.addValueEventListener(new ValueEventListener() {    //DB 값에 대한 이벤트 생성
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("0")) {   //DB 기울기 값이 0일때
                    tvMessage.setText("기울기가 정상입니다.");//textView 메시지값 변경
                    tvMessage.setBackground(getResources().getDrawable(R.drawable.green_circle));  //배경 색 변경
                }
                else if (value.equals("1")) {   //DB의 기울기가 1일 때
                    tvMessage.setText("기울기가 비정상입니다.");   //textView 메시지값 변경
                    tvMessage.setBackground(getResources().getDrawable(R.drawable.red_circle));   //배경 색 변경

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

    }



    public void sub3Button1(View view) {
        finish();
    }
}
