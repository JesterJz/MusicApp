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

import com.build.myapplication.Model.Chude;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChudeAdapter extends ArrayAdapter<Chude> {


    public ChudeAdapter(@NonNull Context context, int resource, @NonNull List<Chude> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView textView;
        ImageView imageViewBackGround;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChudeAdapter.ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_chude,null);
            viewHolder = new ChudeAdapter.ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.txtTitleChuDe);
            viewHolder.imageViewBackGround = convertView.findViewById(R.id.imageViewBackgroundChuDe);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ChudeAdapter.ViewHolder) convertView.getTag();
        }
        Chude chude = getItem(position);
        Picasso.get().load(chude.getHinhChuDe()).into(viewHolder.imageViewBackGround);
        viewHolder.textView.setText(chude.getTenChuDe());
        return convertView;
    }
}
