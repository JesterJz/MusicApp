package com.build.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Activity.PlayMusicActivity;
import com.build.myapplication.Adapter.PlayMusicAdapter;
import com.build.myapplication.Adapter.PlaylistAdapter;
import com.build.myapplication.R;

public class Fragment_list_song_play extends Fragment {
    View view;
    RecyclerView recyclerViewPlayMusic;
    PlayMusicAdapter playMusicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_song_play,container,false);
        recyclerViewPlayMusic = view.findViewById(R.id.recyclerViewPlayMusic);
        if (PlayMusicActivity.songArrayList.size()>0)
        {
            playMusicAdapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.songArrayList);
            recyclerViewPlayMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayMusic.setAdapter(playMusicAdapter);
        }

        return view;
    }


}
