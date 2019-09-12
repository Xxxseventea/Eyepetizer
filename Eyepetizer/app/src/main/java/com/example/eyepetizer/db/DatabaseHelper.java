package com.example.eyepetizer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper  extends SQLiteOpenHelper {
   public static final String NAME = "History";
    private static final int VERSION = 1;
    private static final String SWORD = "SWORD";
    private static final String CREATE_BOOK = "create table History("
            +"id integer primary key autoincrement,"
            + "image text(400),"
            + "title text(400),"
            + "name text(400))";

    //三个构造方法
    //带全部参数的构造方法，此方法不可少
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    //带三个参数的构造函数，调用的其实是所有参数的构造函数
    public DatabaseHelper(Context context,String name,int version){
        this(context,name,null,version);
    }
    //带两个参数的构造函数，调用的是带三个参数的构造函数
    public DatabaseHelper(Context context,String name){
        this(context,name,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(SWORD,"create2222222");
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
             Log.i(SWORD,"successful");
    }


}
