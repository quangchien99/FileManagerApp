package com.qcp.filemanagerapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.qcp.filemanagerapp.R;
import com.qcp.filemanagerapp.constant.Const;
import com.qcp.filemanagerapp.service.Item;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgImages, imgVideos, imgMusics, imgCompressed, imgFavorites, imgApps, imgDocuments, imgDownloads, imgRecent;
    private TextView tvNoImages, tvNoVideos, tvNoMusics, tvNoCompressed, tvNoFavorites, tvNoApps, tvNoDocuments, tvNoDownloads, tvNoRecent;
    private Button btnStorageAnalyzer, btnRecycleBin;
    private boolean isFileManagerInitialized = false;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        setNoItems();
        setOnClickListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && arePermissionsDenied()) {
            requestPermissions(Const.PERMISSIONS, Const.REQUEST_PERMISSION);
            return;
        }
        if (!isFileManagerInitialized) {
            final String rootPath = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            final File dir = new File(rootPath);
            final File[] files = dir.listFiles();
        }
    }

    private boolean arePermissionsDenied() {
        int p = 0;
        while (p < Const.PERMISSION_COUNT) {
            if (checkSelfPermission(Const.PERMISSIONS[p]) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
            p++;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Const.REQUEST_PERMISSION && grantResults.length > 0) {
            if (arePermissionsDenied()) {
                ((ActivityManager) Objects.requireNonNull(this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
                recreate();
            } else {
                recreate();
            }
        }
    }

    private void setOnClickListener() {
        imgImages.setOnClickListener(this);
        imgVideos.setOnClickListener(this);
        imgMusics.setOnClickListener(this);
        imgCompressed.setOnClickListener(this);
        imgFavorites.setOnClickListener(this);
        imgApps.setOnClickListener(this);
        imgDocuments.setOnClickListener(this);
        imgDownloads.setOnClickListener(this);
        imgRecent.setOnClickListener(this);
        btnRecycleBin.setOnClickListener(this);
        btnStorageAnalyzer.setOnClickListener(this);
    }

    private void findViewById() {
        imgImages = findViewById(R.id.img_images);
        imgVideos = findViewById(R.id.img_videos);
        imgMusics = findViewById(R.id.img_musics);
        imgCompressed = findViewById(R.id.img_compressed);
        imgFavorites = findViewById(R.id.img_favorites);
        imgApps = findViewById(R.id.img_apps);
        imgDocuments = findViewById(R.id.img_documents);
        imgDownloads = findViewById(R.id.img_downloads);
        imgRecent = findViewById(R.id.img_recent);
        tvNoImages = findViewById(R.id.tv_number_images);
        tvNoVideos = findViewById(R.id.tv_number_videos);
        tvNoMusics = findViewById(R.id.tv_number_music);
        tvNoCompressed = findViewById(R.id.tv_number_compressed);
        tvNoFavorites = findViewById(R.id.tv_number_favorites);
        tvNoApps = findViewById(R.id.tv_number_apps);
        tvNoDocuments = findViewById(R.id.tv_number_documents);
        tvNoDownloads = findViewById(R.id.tv_number_downloads);
        tvNoRecent = findViewById(R.id.tv_number_recent);
        btnRecycleBin = findViewById(R.id.btn_recycle_bin);
        btnStorageAnalyzer = findViewById(R.id.btn_storage_analyzer);
    }

    private void setNoItems() {
        if (!isFileManagerInitialized) {
            item = new Item();
            tvNoImages.setText(item.getListItems(Environment.DIRECTORY_PICTURES).size() + "");
            tvNoVideos.setText(item.getListItems(Environment.DIRECTORY_MOVIES).size() + "");
            tvNoMusics.setText(item.getListItems(Environment.DIRECTORY_MUSIC).size() + "");
            tvNoCompressed.setText(item.getListItems(Environment.DIRECTORY_DOCUMENTS).size() + "");
            tvNoDownloads.setText(item.getListItems(Environment.DIRECTORY_DOWNLOADS).size() + "");
        }
        isFileManagerInitialized = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_images: {
                Toast.makeText(getApplicationContext(), "Images Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_videos: {
                Toast.makeText(getApplicationContext(), "Videos Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_musics: {
                Toast.makeText(getApplicationContext(), "Musics Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_compressed: {
                Toast.makeText(getApplicationContext(), "Compressed Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_favorites: {
                Toast.makeText(getApplicationContext(), "Favorites Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_apps: {
                Toast.makeText(getApplicationContext(), "Apps Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_documents: {
                Toast.makeText(getApplicationContext(), "Documents Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_downloads: {
                Toast.makeText(getApplicationContext(), "Downloads Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ShowDownloadsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.img_recent: {
                Toast.makeText(getApplicationContext(), "Recent Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_storage_analyzer: {
                Toast.makeText(getApplicationContext(), "Storage analyzer Clicked", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.btn_recycle_bin: {
                Toast.makeText(getApplicationContext(), "Recycle Bin Clicked", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}