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

import com.build.myapplication.Activity.PlayMusicActivity;
import com.build.myapplication.Model.Song;
import com.build.myapplication.R;

import java.util.ArrayList;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayList;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.line_list_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtIndexSOng.setText(position + 1 + "");
        holder.txtNameSong.setText(song.getTenBaiHat());
        holder.txtSinger.setText(song.getCaSi());
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndexSOng, txtNameSong, txtSinger;
        ImageView imgLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndexSOng = itemView.findViewById(R.id.textviewIndexListSong);
            txtNameSong = itemView.findViewById(R.id.textviewNameSong);
            txtSinger = itemView.findViewById(R.id.textviewNameSinger);
            imgLikes = itemView.findViewById(R.id.imageviewlovelistsong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song",songArrayList.get(getPosition()));
                    PlayMusicActivity.mediaPlayer.stop();
                    PlayMusicActivity.mediaPlayer.release();
                    context.startActivity(intent);
                }
            });
        }
    }
}
