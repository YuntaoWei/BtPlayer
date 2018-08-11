package com.ghost.thunder.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyt on 2018/8/9.
 */

public class PermissionActivity extends AppCompatActivity {

    boolean hasPermission = false;
    private static final int REQUEST_CODE = 0x1;

    private static String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasPermission = grantAllPermission();
        } else {
            hasPermission = true;
        }
    }

    private boolean grantAllPermission() {
        ArrayList<String> permission = new ArrayList<>();
        for (String p : PERMISSIONS
             ) {
            if(PackageManager.PERMISSION_GRANTED != checkSelfPermission(p)) {
                permission.add(p);
            }
        }

        if(permission.size() == 0)
            return true;

        grantPermission(permission);
        return false;
    }

    private void grantPermission(List<String> permissions) {
        String[] needRequestPermissions = new String[permissions.size()];
        permissions.toArray(needRequestPermissions);
        requestPermissions(needRequestPermissions, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i : grantResults
             ) {
            if(i != PackageManager.PERMISSION_GRANTED) {
                onGotPermissionFailure();
                return;
            }
        }

        onGotPermissionSuccess();
    }

    void onGotPermissionSuccess() {}

    void onGotPermissionFailure() {}

    void showPermissionTips() {

    }

}
