package com.spot.actapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    EditText nameEditText;
    Spinner spinner;
    String[] countries = new String[]{"india","usa","uk"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.editTextname);
        Log.i(TAG,"activity created");
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,//layout for each row
                countries);//data

        ListView listView = findViewById(R.id.listview);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"activity started");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"activity paused");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"activity resume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"activity stopped");

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
                Intent dialIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:123456789"));
                startActivity(dialIntent);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String lang = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, lang, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
