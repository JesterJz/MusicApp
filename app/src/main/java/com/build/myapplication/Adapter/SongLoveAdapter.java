package com.build.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Model.SongLove;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SongLoveAdapter extends RecyclerView.Adapter<SongLoveAdapter.ViewHolder> {
    Context context;
    ArrayList<SongLove> songLoveArrayList;

    public SongLoveAdapter(Context context, ArrayList<SongLove> songLoveArrayList) {
        this.context = context;
        this.songLoveArrayList = songLoveArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_songlove,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongLove songLove = songLoveArrayList.get(position);
        holder.txtTenCasi.setText(songLove.getCaSi());
        holder.txtTenBaiHat.setText(songLove.getTenBaiHat());
        Picasso.get().load(songLove.getHinhBaiHat()).into(holder.imgBaiHat);
    }

    @Override
    public int getItemCount() {
        return songLoveArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenCasi,txtTenBaiHat;
        ImageView imgBaiHat,imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenBaiHat = itemView.findViewById(R.id.textviewTenBaiHat);
            txtTenCasi = itemView.findViewById(R.id.textviewTenCaSi);
            imgBaiHat = itemView.findViewById(R.id.imageSongLove);
            imgLuotThich = itemView.findViewById(R.id.imageviewlove);
        }
    }
}
