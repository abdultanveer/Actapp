package com.spot.actapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    LayoutInflater mInflater;
    ArrayList<Word> mWordArrayList;
    public WordListAdapter(Context context, List<Word> data) {
        mInflater = LayoutInflater.from(context);
        mWordArrayList = (ArrayList<Word>) data;
    }

    @NonNull
    @Override//box holding the recycled planks
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View plank = mInflater.inflate(R.layout.rv_row_layout,parent,false); //buy plank
        return new WordViewHolder(plank,this);
    }

    //write the data on plank
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = mWordArrayList.get(position);
        holder.wordTextView.setText(word.word);
    }

    @Override
    public int getItemCount() {
        return mWordArrayList.size();
    }


    //plank with data
    public class WordViewHolder extends RecyclerView.ViewHolder {
       public TextView wordTextView;

        public WordViewHolder(View plank, WordListAdapter wordListAdapter) {
            super(plank);
            wordTextView = plank.findViewById(R.id.textViewrow);
        }
    }
}
