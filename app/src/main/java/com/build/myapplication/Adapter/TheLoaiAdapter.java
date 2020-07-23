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
import com.build.myapplication.Model.TheLoai;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_theloai,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = theLoaiArrayList.get(position);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(holder.imageView);
        holder.textView.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewBackgroundTheLoai);
            textView = itemView.findViewById(R.id.txtTitleTheLoai);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListBaiHatActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
