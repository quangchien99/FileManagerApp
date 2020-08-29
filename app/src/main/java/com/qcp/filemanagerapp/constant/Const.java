package com.qcp.filemanagerapp.constant;

import android.Manifest;
import android.os.Environment;

public class Const {
    public static final int REQUEST_PERMISSION = 1234;
    public static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static final int PERMISSION_COUNT = 2;
   // public static final String rootPath = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
}
