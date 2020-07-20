package com.build.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.build.myapplication.Model.Playlist;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView textView;
        ImageView imageViewBackGround,imageViewIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.txtTitlePlaylist);
            viewHolder.imageViewBackGround = convertView.findViewById(R.id.imageViewBackgroundPlaylist);
            viewHolder.imageViewIcon = convertView.findViewById(R.id.imgIconPlaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.get().load(playlist.getHinhNen()).into(viewHolder.imageViewBackGround);
        Picasso.get().load(playlist.getHinhIcon()).into(viewHolder.imageViewIcon);
        viewHolder.textView.setText(playlist.getTen());
        return convertView;
    }
}
