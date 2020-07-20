package com.build.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.build.myapplication.Activity.ListBaiHatActivity;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }
    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_banner,null);

        ImageView ImgBackGroundBanner = view.findViewById(R.id.imageViewBackgroundBanner);
        ImageView ImgSongBanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleSong = view.findViewById(R.id.textviewtitlebaihat);
        TextView txtNoiDungSong = view.findViewById(R.id.textviewnoidung);

        Picasso.get().load(arrayListBanner.get(position).getHinhAnhAds()).into(ImgBackGroundBanner);
        Picasso.get().load(arrayListBanner.get(position).getHinhBaiHat()).into(ImgSongBanner);
        txtTitleSong.setText(arrayListBanner.get(position).getTenBaiHat());
        txtNoiDungSong.setText(arrayListBanner.get(position).getNoidungAds());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListBaiHatActivity.class);
                intent.putExtra("Ads",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
