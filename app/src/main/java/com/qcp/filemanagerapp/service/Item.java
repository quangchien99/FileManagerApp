package com.qcp.filemanagerapp.service;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Item {
    public Item() {
    }

    public List<File> getListItems(String path) {
        List<File> files = new ArrayList<>();
        String rootPath = String.valueOf(Environment.getExternalStoragePublicDirectory(path));
        File dir = new File(rootPath);
        File[] arrFiles = dir.listFiles();
        if (arrFiles == null) {
            return files;
        }
        Log.d("qcpp", "Length of arrfiles in: " + path + " is:" + arrFiles.length);
        files = new ArrayList<>();
        for (int i = 0; i < arrFiles.length; i++) {
            files.add(arrFiles[i]);
        }
        return files;
    }
}
