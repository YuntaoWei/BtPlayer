package com.ghost.thunder.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class StorageUtils {

    private static final String TAG = "StorageUtils";

    private static final String DEFAULT_PATH = File.separator + "BtPlayer" + File.separator + "download";
    private static final String STORAGE_PREF_FILE_NAME = "storage";
    private static final String PATH = "path";

    public static String getDefaultSavePath(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(STORAGE_PREF_FILE_NAME, Context.MODE_PRIVATE);
        String path = sp.getString(PATH, null);
        if(!TextUtils.isEmpty(path)) {
            return path;
        }

        File f = new File(Environment.getExternalStorageDirectory().toString() + DEFAULT_PATH);
        if(!f.exists()) {
            if(!f.mkdirs()) {
                return Environment.getExternalStorageDirectory().toString() + File.separator;
            }

        }

        return Environment.getExternalStorageDirectory().toString() + DEFAULT_PATH;
    }

    public static boolean changeSavePath(Context ctx, String path) {
        File f = new File(path);

        if(!f.exists()) {
            if(!f.mkdirs()) {
                return false;
            }
        }

        SharedPreferences sp = ctx.getSharedPreferences(STORAGE_PREF_FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(PATH, path).apply();

        return true;
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f M" : "%.1f M", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f K" : "%.1f K", f);
        } else
            return String.format("%d B", size);
    }

    public static boolean isTorrentFile(String path) {
        File f = new File(path);
        BufferedReader br = null;
        if(f.exists()) {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String msg = br.readLine();
                LogPrinter.i(TAG, "isTorrentFile : " + path + " \n" + msg);
                if(!TextUtils.isEmpty(msg) && msg.contains("udp://tracker")) {
                    return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    br = null;
                }
            }
        }

        return false;
    }

}
