package com.example.smartlock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    private static final int CALL_FOR_CREATION1 = 0;
    private static final int CALL_FOR_CREATION2 = 1;
    private static final int CALL_FOR_CREATION3 = 2;
    private static final int CALL_FOR_CREATION4 = 3;


    private Button sendbt1;
    private Button sendbt2;
    private Button sendbt3;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();   //데이터베이스의 인스턴스 불러옴
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendbt1 = (Button) findViewById(R.id.button14);
        sendbt2 = (Button) findViewById(R.id.button15);
        sendbt3 = (Button) findViewById(R.id.button16);

        sendbt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("lockcommand").setValue("1");   //lockcommand 값에 1으로 세팅
            }
        });

        sendbt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("lockcommand").setValue("0");   //lockcommand 값에 0으로 세팅
            }
        });

        sendbt3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("buzzer").setValue("0");  // buzzer 값을 0으로 세팅
            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child("lockstate").addValueEventListener(new ValueEventListener() {  //lockstate 이벤트 생성
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {   //데이터의 변경이 있을 경우
                String value = dataSnapshot.getValue(String.class);   //바껴진 데이터를 value 변수에 넣음
                if(value.equals("0")){     //값이 0일 경우
                    createNotification1();  //팝업 메시지 출력
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("tilt").addValueEventListener(new ValueEventListener() { //데이터베이스 tilt 이벤트 생성
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("1")){    //바껴진 값이 1일 경우
                    createNotification2();   //팝업 메시지 출력
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("buzzer").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("1")){
                    createNotification3();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void createNotification3() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("SmartLock");
        builder.setContentText("경고 : 비밀번호 3회오류");

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(3, builder.build());
    }

    private void createNotification1() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("SmartLock");
        builder.setContentText("금고가 열렸습니다.");

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }

    private void createNotification2() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("SmartLock");
        builder.setContentText("기울기가 비정상입니다.");

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(2, builder.build());
    }


    public void onButton1Click(View view) {
        Intent intent = new Intent(this, sub1_lock.class);
        startActivityForResult(intent, CALL_FOR_CREATION1);
    }

    public void onButton2Click(View view) {
        Intent intent = new Intent(this, submain2.class);
        startActivityForResult(intent, CALL_FOR_CREATION2);
    }

    public void onButton3Click(View view) {
        Intent intent = new Intent(this, submain3.class);
        startActivityForResult(intent, CALL_FOR_CREATION3);
    }

    public void onButton4Click(View view) {
        Intent intent = new Intent(this, submain4.class);
        startActivityForResult(intent, CALL_FOR_CREATION4);
    }
}



