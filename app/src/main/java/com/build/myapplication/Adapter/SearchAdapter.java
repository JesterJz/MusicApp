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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> songArrayListSearch;

    public SearchAdapter(Context context, ArrayList<Song> songArrayListSearch) {
        this.context = context;
        this.songArrayListSearch = songArrayListSearch;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_search_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayListSearch.get(position);
        Picasso.get().load(song.getHinhBaiHat()).into(holder.imageViewSong);
        holder.textViewNameSong.setText(song.getTenBaiHat());
        holder.textViewNameSinger.setText(song.getCaSi());
    }

    @Override
    public int getItemCount() {
        return songArrayListSearch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewSong;
        TextView textViewNameSong, textViewNameSinger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSong = itemView.findViewById(R.id.imageviewSearchSong);
            textViewNameSong = itemView.findViewById(R.id.textviewSearchSong);
            textViewNameSinger = itemView.findViewById(R.id.textviewCaSi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song",songArrayListSearch.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
