package com.build.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.build.myapplication.Activity.ListBaiHatActivity;
import com.build.myapplication.Model.Album;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public int getCount() {
        return albumArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imageViewAlbum;
        TextView txtTenAlbum,txtSinger;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.line_album,parent,false);
            viewHolder.txtTenAlbum = convertView.findViewById(R.id.TenAlbum);
            viewHolder.txtSinger = convertView.findViewById(R.id.TenSingerAlbum);
            viewHolder.imageViewAlbum = convertView.findViewById(R.id.imgAlbum);
            convertView.setTag(viewHolder);
            
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            Album album = albumArrayList.get(position);
            viewHolder.txtTenAlbum.setText(album.getTenAlbum());
            viewHolder.txtSinger.setText(album.getTenCaSiAlbum());
            Picasso.get().load(album.getHinhAlbum()).into(viewHolder.imageViewAlbum);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBaiHatActivity.class);
                intent.putExtra("idalbum",albumArrayList.get(position));
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
