package com.example.eyepetizer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eyepetizer.bean.HistoryBean;
import com.example.eyepetizer.view.activity.History;

import java.util.ArrayList;
import java.util.List;

public class SearchListDbOperation {
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;
    String tablename;
    ArrayList<HistoryBean> historyBeans;

    public SearchListDbOperation(Context context,String tablename){
        databaseHelper = new DatabaseHelper(context,tablename);
        this.tablename = tablename;

    }

    //添加搜索记录
    public void add(String image,String name,String title){
   //     databaseHelper.onCreate(database);
        databaseHelper.getWritableDatabase();
        database = databaseHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("image",image);
        values.put("title",title);
        database.insert(tablename,null,values);
      values.clear();
    }

    //查询历史纪录
    public void check(){
        database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(tablename,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                Log.d("tag","successful2222"+name);
                Log.d("tag","successful2222"+image);
                Log.d("tag","successful222222"+title);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void addList(ArrayList<HistoryBean> histories){
        this.historyBeans  = histories;

        database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.query(tablename,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                HistoryBean historyBean = new HistoryBean();
               historyBean.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
               historyBean.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
               historyBean.setImage(cursor.getString(cursor.getColumnIndexOrThrow("image")));
             //   Log.d("tag","successful2222"+name);
               // Log.d("tag","successful2222"+image);
               // Log.d("tag","successful222222"+title);
                histories.add(historyBean);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }

    public void deleteData(ArrayList<HistoryBean> historyBeans,String tablename){
        this.historyBeans = historyBeans;
        this.tablename = tablename;
        database = databaseHelper.getWritableDatabase();
        database.delete(tablename,null,null);
        for(int i = 0;i<historyBeans.size();i++){
            historyBeans.remove(i);
        }
    }

}
