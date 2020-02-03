package com.spot.actapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_msg);
       String data = getIntent().getExtras().getString("actkey");

        TextView resTextView = findViewById(R.id.textViewResult);
        resTextView.setText(data);
    }
}
