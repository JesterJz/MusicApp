package com.build.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Activity.ListBaiHatActivity;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlaylistByChudeAdapter extends RecyclerView.Adapter<ListPlaylistByChudeAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public ListPlaylistByChudeAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_playlist_by_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlistArrayList.get(position);
        holder.textViewTenPlaylist.setText(playlist.getTen());
        Picasso.get().load(playlist.getHinhIcon()).into(holder.imageViewPlaylist);
    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPlaylist;
        TextView textViewTenPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPlaylist = itemView.findViewById(R.id.imageView_playlist_by_chude);
            textViewTenPlaylist = itemView.findViewById(R.id.textView_Ten_Playlist_by_chude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListBaiHatActivity.class);
                    intent.putExtra("idplaylist",playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
