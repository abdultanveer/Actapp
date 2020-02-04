package com.spot.actapp.database;
import com.spot.actapp.database.TodoContract.TodoEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Dao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }
    public void closeDb(){
        database.close();
    }

    public void createRow(String title, String subTitle){
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_TITLE,title);
        values.put(TodoEntry.COLUMN_NAME_SUBTITLE,subTitle);
        database.insert(TodoEntry.TABLE_NAME,null,values);
    }
    public String readRow(){
      // Cursor cursor = database.rawQuery("select * from todo",null);
        Cursor cursor = database.query(TodoEntry.TABLE_NAME,null,null,null,null,
                null,null);
        cursor.moveToLast();
        int titleColIndex = cursor.getColumnIndexOrThrow(TodoEntry.COLUMN_NAME_TITLE);
        int subtitleColIndex = cursor.getColumnIndexOrThrow(TodoEntry.COLUMN_NAME_SUBTITLE);

      return  cursor.getString(titleColIndex)+"\n"+
              cursor.getString(subtitleColIndex);

    }
    public void updateRow(){}
    public void deleteRow(){}

}
