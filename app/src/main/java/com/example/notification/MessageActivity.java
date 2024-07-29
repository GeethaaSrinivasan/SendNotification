package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {
    TextView msgtv;
    String value;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        msgtv=findViewById(R.id.Message_tv);
        value=getIntent().getStringExtra("Message");
        msgtv.setText(value);

    }
}