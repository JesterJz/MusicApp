package com.build.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Model.Album;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumHotAdapter extends RecyclerView.Adapter<AlbumHotAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> arrayAlbumHot;

    public AlbumHotAdapter(Context context, ArrayList<Album> arrayAlbumHot) {
        this.context = context;
        this.arrayAlbumHot = arrayAlbumHot;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_albumhot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = arrayAlbumHot.get(position);
        holder.txtTenAlbum.setText(album.getTenAlbum());
        holder.txtSinger.setText(album.getTenCaSiAlbum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imageViewAlbum);
    }

    @Override
    public int getItemCount() {
        return arrayAlbumHot.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAlbum;
        TextView txtTenAlbum,txtSinger;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAlbum = itemView.findViewById(R.id.imgAlbum);
            txtSinger = itemView.findViewById(R.id.TenSinger);
            txtTenAlbum = itemView.findViewById(R.id.TenAlbumHot);
        }
    }

}
