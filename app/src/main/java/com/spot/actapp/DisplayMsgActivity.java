package com.spot.actapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    public void handleClick(View view) {
        EditText macEditText = findViewById(R.id.editTextmac);
        String mac = macEditText.getText().toString();
        Intent dataIntent = new Intent();
        dataIntent.putExtra("macaddress",mac);
        setResult(RESULT_OK,dataIntent);
        finish();
    }
}
