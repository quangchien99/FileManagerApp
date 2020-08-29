package com.qcp.filemanagerapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qcp.filemanagerapp.R;
import com.qcp.filemanagerapp.utils.ItemClickListener;

import java.io.File;
import java.util.Date;
import java.util.List;

public class DownloadsAdapter extends RecyclerView.Adapter<DownloadsAdapter.ViewHolder>  {
    private List<File> files;
    private Context context;
    private ItemClickListener itemClickListener;

    public DownloadsAdapter(List<File> files, Context context,ItemClickListener itemClickListener) {
        this.files = files;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DownloadsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_downloads, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadsAdapter.ViewHolder holder, int position) {
        File file = files.get(position);
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        holder.imgDownloadsItemAvatar.setImageBitmap(myBitmap);
        String filename = file.getPath().substring(file.getPath().lastIndexOf("/") + 1);
        holder.tvDownloadsItemName.setText(filename);
        Date lastModDate = new Date(file.lastModified());
        String date = lastModDate.toString().substring(0, 20);
        holder.tvDownloadsItemDate.setText(date);
        int fileSize = Integer.parseInt(String.valueOf(file.length() / 1024));
        holder.tvDownloadsItemCapacity.setText(fileSize + " KB");
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgDownloadsItemAvatar;
        TextView tvDownloadsItemName, tvDownloadsItemDate, tvDownloadsItemCapacity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDownloadsItemAvatar = itemView.findViewById(R.id.item_downloads_avatar);
            tvDownloadsItemName = itemView.findViewById(R.id.item_downloads_name);
            tvDownloadsItemDate = itemView.findViewById(R.id.item_downloads_datetime);
            tvDownloadsItemCapacity = itemView.findViewById(R.id.item_downloads_capacity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(getAdapterPosition());
        }
    }
}
