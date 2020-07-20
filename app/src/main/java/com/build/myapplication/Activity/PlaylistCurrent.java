package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.build.myapplication.Adapter.BannerAdapter;
import com.build.myapplication.Adapter.PlaylistAdapter;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistCurrent extends AppCompatActivity {
    Toolbar toolbar;
    PlaylistAdapter playlistAdapter;
    ArrayList<Playlist> playlistArrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_current);
        AnhXa();
        init();
        GetData();
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlistArrayList = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(PlaylistCurrent.this,android.R.layout.simple_expandable_list_item_1,playlistArrayList);
                listView.setAdapter(playlistAdapter);
            }
            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarListPlayList);
        listView = findViewById(R.id.ListTitlePlaylist);
    }
}