package com.build.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.build.myapplication.R;
import com.squareup.picasso.Picasso;

public class Fragment_music_disc extends Fragment {
    View view;
    ImageView imageViewBanner, imageViewDisc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music_disc, container, false);
        imageViewBanner = view.findViewById(R.id.imageviewbannerdisc);
        imageViewDisc = view.findViewById(R.id.imageviewdiscmusic);
        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.get().load(hinhanh).into(imageViewBanner);
        Picasso.get().load(hinhanh).into(imageViewDisc);
    }
}
