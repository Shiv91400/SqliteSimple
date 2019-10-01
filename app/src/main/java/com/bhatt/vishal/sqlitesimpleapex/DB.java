package com.bhatt.vishal.sqlitesimpleapex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB extends SQLiteOpenHelper {
    public DB(Context context) {

        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userinfo (username text primary key, password text, phone text)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean loginUser(String username,String password)
    {
       SQLiteDatabase db = getReadableDatabase();
      Cursor cursor =   db.query("userinfo",null,"username = ? and password = ?",new String[]{username,password},null,null,null);

        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    long registerUser(String username,String password, String phone)
    {
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("phone",phone);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("userinfo",null,cv);
    }

    long deleteUser(String username)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("userinfo","username = ?",new String[]{username});
    }

    Cursor getUserDetail(String username)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.query("userinfo",null,"username=?",new String[]{
                username},null,null,null);
    }

    long updateUser(String username,String password, String phone)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("phone",phone);
        return  db.update("userinfo",cv,"username=?",new String[]{username});
    }
}
