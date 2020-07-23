package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.build.myapplication.Adapter.ListPlaylistByChudeAdapter;
import com.build.myapplication.Model.Chude;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPlaylistByChuDe_Activity extends AppCompatActivity {

    Chude chude;
    RecyclerView recyclerViewTheLoaiTheoChude;
    ListPlaylistByChudeAdapter listPlaylistByChudeAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_playlist_by_chu_de);
        getDataIntent();
        getDataService();
        init();
    }

    private void getDataService() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callList = dataService.GetListTheLoaiByChude(chude.getIDChuDe());
        callList.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                listPlaylistByChudeAdapter = new ListPlaylistByChudeAdapter(ListPlaylistByChuDe_Activity.this,playlistArrayList);
                recyclerViewTheLoaiTheoChude.setLayoutManager(new GridLayoutManager(ListPlaylistByChuDe_Activity.this,2));
                recyclerViewTheLoaiTheoChude.setAdapter(listPlaylistByChudeAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheLoaiTheoChude = findViewById(R.id.recycerViewTheLoaiTheoChude);
        toolbar = findViewById(R.id.toolbarlistTheLoaiTheoChude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(chude.getTenChuDe());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("idchude")){
            chude = (Chude) intent.getSerializableExtra("idchude");
        }
    }
}