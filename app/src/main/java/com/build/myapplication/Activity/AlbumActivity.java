package com.build.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.GridView;
import android.widget.TextView;

import com.build.myapplication.Adapter.AlbumAdapter;
import com.build.myapplication.Adapter.AlbumHotAdapter;
import com.build.myapplication.Model.Album;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {
    Toolbar toolbar;
    GridView gridView;
    GridLayout gridLayout;
    AlbumHotAdapter albumAdapter;
    ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        AnhXa();
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> listAlbum = dataService.GetAlBum();
        listAlbum.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                AlbumAdapter albumAdapter = new AlbumAdapter(AlbumActivity.this,albumArrayList);
//                gridView.setHorizontalSpacing(30);
                gridView.setVerticalSpacing(30);
                gridView.setAdapter(albumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d("BBB","don't get data from Api Album");

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ALBUM");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarAlbum);
        gridView = findViewById(R.id.gridViewAlbum);
    }
}