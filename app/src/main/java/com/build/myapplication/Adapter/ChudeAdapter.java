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

import com.build.myapplication.Activity.ListPlaylistByChuDe_Activity;
import com.build.myapplication.Model.Chude;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChudeAdapter extends RecyclerView.Adapter<ChudeAdapter.ViewHolder> {
    Context context;
    ArrayList<Chude> chudeArrayList;

    public ChudeAdapter(Context context, ArrayList<Chude> chudeArrayList) {
        this.context = context;
        this.chudeArrayList = chudeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chude chude = chudeArrayList.get(position);
        Picasso.get().load(chude.getHinhChuDe()).into(holder.imageViewbgrChude);
        holder.textViewtitleChude.setText(chude.getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return chudeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewbgrChude;
        TextView textViewtitleChude;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewbgrChude = itemView.findViewById(R.id.imageViewBackgroundChuDe);
            textViewtitleChude = itemView.findViewById(R.id.txtTitleChuDe);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListPlaylistByChuDe_Activity.class);
                    intent.putExtra("idchude",chudeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

//    public ChudeAdapter(@NonNull Context context, int resource, @NonNull List<Chude> objects) {
//        super(context, resource, objects);
//    }
//    class ViewHolder{
//        TextView textView;
//        ImageView imageViewBackGround;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ChudeAdapter.ViewHolder viewHolder = null;
//        if (convertView == null){
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.line_chude,null);
//            viewHolder = new ChudeAdapter.ViewHolder();
//            viewHolder.textView = convertView.findViewById(R.id.txtTitleChuDe);
//            viewHolder.imageViewBackGround = convertView.findViewById(R.id.imageViewBackgroundChuDe);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ChudeAdapter.ViewHolder) convertView.getTag();
//        }
//        Chude chude = getItem(position);
//        Picasso.get().load(chude.getHinhChuDe()).into(viewHolder.imageViewBackGround);
//        viewHolder.textView.setText(chude.getTenChuDe());
//        return convertView;
//    }
}
