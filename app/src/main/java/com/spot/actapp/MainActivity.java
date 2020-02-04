package com.spot.actapp;
import com.spot.actapp.database.TodoContract.TodoEntry;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.spot.actapp.database.Dao;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName() ;
    EditText nameEditText,pwdEditText;
    Spinner spinner;
    String[] countries = new String[]{"india","usa","uk"};
    public static String FILE_NAME = "credentials";
    public  static int MODE = MODE_PRIVATE;
    Dao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new Dao(this);
        dao.openDb();
        nameEditText = findViewById(R.id.editTextname);
        pwdEditText = findViewById(R.id.editText2);

        Log.i(TAG,"activity created");
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,//layout for each row
                countries);//data*/
        Cursor cursor = dao.getRows();
        SimpleCursorAdapter adapter =  new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,//row layout
                cursor,//data
                new String[]{TodoEntry.COLUMN_NAME_TITLE, TodoEntry.COLUMN_NAME_SUBTITLE}, //db cols
                new int[] {android.R.id.text1,android.R.id.text2} //textview ids
                );

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

        savePrefs();

    }

    private void savePrefs() {
        //create a file -credentials
        //data/data/com.spot.actapp/sharefprefs
        SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE);
        //open the file in edit
        SharedPreferences.Editor editor = preferences.edit();
        String name = nameEditText.getText().toString();
        //write to file [data from edittexts]
        editor.putString("key",name);
        //save the file
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"activity resume");
        restorePrefs();
    }

    private void restorePrefs() {
        //open the file
        SharedPreferences preferences = getSharedPreferences(FILE_NAME,MODE);
        //read the file
        String name = preferences.getString("key","");
        //set the the credentials
        nameEditText.setText(name);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"activity stopped");

    }

    public void clickHandler(View view) {
        switch (view.getId()){
            case R.id.buttonlogin:
                //startactivtityResult();
                saveDb();
                //dao.closeDb();
                break;
            case R.id.buttoncancel:
                //lauchIntent();
                getDb();
                break;
        }
        //String welcome = getResources().getString(R.string.welcome);



    }

    private void getDb() {
        String data = dao.readRow();
        TextView textView = findViewById(R.id.textViewMac);
        textView.setText(data);
    }

    private void saveDb() {
        String title = nameEditText.getText().toString();
        String subTitle = pwdEditText.getText().toString();

        dao.createRow(title,subTitle);
    }

    private void lauchIntent() {
        Intent dialIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("tel:123456789"));
        startActivity(dialIntent);
                /*String name = nameEditText.getText().toString();
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                */
    }

    private void startactivtityResult() {
        Intent homeIntent = new Intent(MainActivity.this,
                DisplayMsgActivity.class);
        String name = nameEditText.getText().toString();
        homeIntent.putExtra("actkey",name);
        startActivityForResult(homeIntent,007);
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
