package com.example.m2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FlowDatabase extends SQLiteOpenHelper {

    private final static String aaa = "search";
    private final SQLiteDatabase db;

    public FlowDatabase(Context context){
        super(context,"hehe",null,1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aaa(personid integer primary key autoincrement,keys varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //插入数据
    public void insert(String keys){
        ContentValues values = new ContentValues();
        values.put("keys",keys);
        db.insert(aaa,null,values);
    }

    //删除数据
    public void delete(){
        db.delete(aaa,null,null);
    }


    //查询数据
    public List<String> query(){
        Cursor cursor = db.query(aaa,null,null,null,null,null,null);
        List<String> list = new ArrayList<>();
        while (cursor.moveToNext()){
            String keys = cursor.getString(cursor.getColumnIndex("keys"));
            list.add(keys);
        }
        return list;
    }
}