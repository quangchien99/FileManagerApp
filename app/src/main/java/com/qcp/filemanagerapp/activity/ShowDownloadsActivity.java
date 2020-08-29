package com.qcp.filemanagerapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qcp.filemanagerapp.R;
import com.qcp.filemanagerapp.adapter.DownloadsAdapter;
import com.qcp.filemanagerapp.service.Item;
import com.qcp.filemanagerapp.utils.ItemClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowDownloadsActivity extends AppCompatActivity implements ItemClickListener {
    private TextView tvPathOutput;
    private RecyclerView rcvDownloads;
    private List<File> files;
    private DownloadsAdapter downloadsAdapter;
    private boolean isFileManagerInitialized = false;
    private Item item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_downloads);
        findViewById();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setListFiles();
        downloadsAdapter = new DownloadsAdapter(files, this, this);
        rcvDownloads.setAdapter(downloadsAdapter);
        rcvDownloads.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setListFiles() {
        if (!isFileManagerInitialized) {
            item = new Item();
            files = item.getListItems(Environment.DIRECTORY_DOWNLOADS);
            isFileManagerInitialized = true;
        }
    }

    private void findViewById() {
        tvPathOutput = findViewById(R.id.tv_path_output);
        rcvDownloads = findViewById(R.id.rcv_downloads);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getApplicationContext(), "Clicked item number: " + position, Toast.LENGTH_SHORT).show();
        File file = files.get(position);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file);
        intent.setDataAndType(data, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }
}
