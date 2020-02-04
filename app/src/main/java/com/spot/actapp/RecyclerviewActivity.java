package com.spot.actapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ArrayList<Word> wordArrayList = new ArrayList<>();
        wordArrayList.add(new Word("one"));
        wordArrayList.add(new Word("tow"));
        wordArrayList.add(new Word("three"));
        wordArrayList.add(new Word("four"));
        wordArrayList.add(new Word("five"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        WordListAdapter adapter = new WordListAdapter(this,wordArrayList);
        recyclerView.setAdapter(adapter);
    }
}
