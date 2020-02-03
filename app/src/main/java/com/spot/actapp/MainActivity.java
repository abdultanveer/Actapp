package com.spot.actapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
                startActivityForResult(homeIntent,007);
                break;
            case R.id.buttoncancel:
                /*String name = nameEditText.getText().toString();
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                */break;
        }
        //String welcome = getResources().getString(R.string.welcome);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==007 && resultCode == RESULT_OK){
            String macaddr = intent.getExtras().getString("macaddress");
            TextView macTextView = findViewById(R.id.textViewMac);
            macTextView.setText(macaddr);
        }
    }
}
