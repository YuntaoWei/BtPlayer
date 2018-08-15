package com.ghost.thunder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yuntao.wei on 2018/8/15.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class DownLoadDBUtil {

    private static DownLoadDBUtil mInstance;
    private DownLoadDataBase dataBase;
    private SQLiteDatabase db;

    private DownLoadDBUtil(Context ctx) {
        dataBase = new DownLoadDataBase(ctx);
    }

    public static DownLoadDBUtil getInstance(Context ctx) {
        if(mInstance == null)
            mInstance = new DownLoadDBUtil(ctx);

        return mInstance;
    }

    private void checkEnvironment() {
        if(db == null)
            db = dataBase.getWritableDatabase();
    }

    public long insert(ContentValues values) {
        checkEnvironment();
        return db.insert(DownLoadDataBase.DOWNLOAD_TABLE_NAME, null, values);
    }

    public int deleteById(int id) {
        checkEnvironment();
        return db.delete(DownLoadDataBase.DOWNLOAD_TABLE_NAME,
                DownLoadDataBaseColumns.ID +" = " + id, null);
    }

    public int deleteByName(String name) {
        checkEnvironment();
        return db.delete(DownLoadDataBase.DOWNLOAD_TABLE_NAME,
                DownLoadDataBaseColumns.NAME + " = " + name, null);
    }

    public int updateById(int id, ContentValues cv) {
        checkEnvironment();
        return db.update(DownLoadDataBase.DOWNLOAD_TABLE_NAME, cv,
                DownLoadDataBaseColumns.ID + "=" + id, null);
    }

    public int updateByName(String name, ContentValues cv) {
        checkEnvironment();
        return db.update(DownLoadDataBase.DOWNLOAD_TABLE_NAME, cv,
                DownLoadDataBaseColumns.NAME + "=" + name, null);
    }

    public Cursor queryAll() {
        checkEnvironment();
        return db.query(DownLoadDataBase.DOWNLOAD_TABLE_NAME,
                null, null, null, null, null, null);
    }

    public Cursor rawQuery(String sql, String[] args) {
        checkEnvironment();
        return db.rawQuery(sql, args);
    }

    public void execSql(String sql) {
        checkEnvironment();
        db.execSQL(sql);
    }

    public void release() {
        if(db != null)
            db.close();
        db = null;
    }

}
