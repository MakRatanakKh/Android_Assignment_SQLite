package com.example.mak_ratanak_assignment_sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.mak_ratanak_assignment_sqlite.model.UserModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="user_db";
    public static final String TABLE_USER="users";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER +
                "(id integer primary key autoincrement, email text, password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_USER);
        onCreate(db);
    }

    public void insertUser(String email, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);

        db.insert(TABLE_USER, null, values);
        db.close();
        Log.d("Insert Data::", "Insert Successful.");
    }

    public ArrayList<UserModel> getAllUser(){
        ArrayList<UserModel> arrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor  = db.rawQuery("select * from " + TABLE_USER, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            UserModel model = new UserModel();

            model.setEmail(cursor.getString(1));
            model.setPassword(cursor.getString(2));
            arrayList.add(model);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
