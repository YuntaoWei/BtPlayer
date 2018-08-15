package com.ghost.thunder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuntao.wei on 2018/8/15.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class DownLoadDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "download.db";
    public static final String DOWNLOAD_TABLE_NAME = "download";

    public DownLoadDataBase(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "create table "+ DOWNLOAD_TABLE_NAME +" (" +
                DownLoadDataBaseColumns.ID + " integer primary key autoincrement,"+
                DownLoadDataBaseColumns.NAME + " varchar(40),"+
                DownLoadDataBaseColumns.SAVE_PATH + " varchar(50),"+
                DownLoadDataBaseColumns.URL + " varchar(50),"+
                DownLoadDataBaseColumns.PROGRESS + " integer"+
                ")";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
