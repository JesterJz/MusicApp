package com.build.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.build.myapplication.Activity.AlbumActivity;
import com.build.myapplication.Activity.PlaylistActivity;
import com.build.myapplication.R;

public class Fragment_General extends Fragment {
    ImageView song,singer,playlist,album;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general,container,false);
        anhxa();
        listenView();
        return view;
    }

    private void listenView() {
        singer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"ca si",Toast.LENGTH_LONG).show();
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("BBB", String.valueOf(getActivity()));
                //Toast.makeText(getActivity(), string,Toast.LENGTH_LONG).show();
                  Intent intent = new Intent(getActivity(), PlaylistActivity.class);
                  startActivity(intent);
            }
        });
        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"song",Toast.LENGTH_LONG).show();
            }
        });
        album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(),"album",Toast.LENGTH_LONG).show();
//               Fragment_AlbumHot fragment_albumHot = new Fragment_AlbumHot();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.mainLayout,fragment_albumHot);
//                transaction.commit();
            }
        });
    }

    private void anhxa() {
        song = (ImageView) view.findViewById(R.id.song);
        singer = (ImageView) view.findViewById(R.id.singer);
        playlist = (ImageView) view.findViewById(R.id.playlist);
        album = (ImageView) view.findViewById(R.id.album);
    }
}
