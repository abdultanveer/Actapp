package com.spot.actapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.editTextname);

    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonlogin:
                Intent homeIntent = new Intent(MainActivity.this,
                        DisplayMsgActivity.class);
                String name = nameEditText.getText().toString();
                homeIntent.putExtra("actkey",name);
                startActivity(homeIntent);
                break;
            case R.id.buttoncancel:
                /*String name = nameEditText.getText().toString();
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                */break;
        }
        //String welcome = getResources().getString(R.string.welcome);



    }
}
