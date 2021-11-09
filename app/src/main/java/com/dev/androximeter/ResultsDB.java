package com.dev.androximeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ResultsDB extends SQLiteOpenHelper {

    public ResultsDB(Context context) {
        super(context, "name.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table results (service text, result text, datetime text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists results");
    }

    public boolean insertData(String service, String result, String datetime) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("service", service);
        contentValues.put("result", result);
        contentValues.put("datetime", datetime);
        long ins = MyDB.insert("results", null, contentValues);
        if (ins==-1) return false;
        else return true;
    }


    public ArrayList getAll() {
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ArrayList<String> results = new ArrayList<String>();

        // CONSULTA A LA BASE DE DATOS
        Cursor  cursor = MyDB.rawQuery("select * from results",null);

        // MOVER EL INDICE AL INICIO DE LA TABLA
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String result = cursor.getString(0) + " - " + cursor.getString(1) + "\n" + cursor.getString(2);

                results.add(result);
                cursor.moveToNext();
            }
        }
        return results;
    }

}
