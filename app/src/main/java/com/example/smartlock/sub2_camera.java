package com.example.smartlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class sub2_camera extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2_camera);

        TextView txtLink = (TextView)this.findViewById(R.id.textView3);
        txtLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("http://172.31.100.90:8080/stream"));  //라즈베리파이의 ip주소, 동영상 링크로 이동

                startActivity(intent);
            }

        });
    }

    public void cameraButton1(View view) {
        finish();
    }
}
