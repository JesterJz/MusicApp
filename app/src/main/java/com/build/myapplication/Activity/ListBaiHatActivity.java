package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.build.myapplication.Adapter.ListSongAdapter;
import com.build.myapplication.Model.Album;
import com.build.myapplication.Model.Chude;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.Model.Song;
import com.build.myapplication.Model.TheLoai;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.graphics.Color.*;

public class ListBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerViewListSong;
    Toolbar toolbar;
    ImageButton imageButton;
    ImageView imageView;

    QuangCao quangCao;
    Playlist playlist;
    TheLoai theLoai;
    Chude chude;
    Album album;

    ArrayList<Song> songArrayList;
    Bitmap bitmap;
    ListSongAdapter listSongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        DataIntent();
        AnhXa();
        init();
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhAnhAds(),quangCao.getHinhBaiHat());
            GetDataAds(quangCao.getIDAds());
        }
        if (playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhNen(),playlist.getHinhIcon());
//            Toast.makeText(ListBaiHatActivity.this,playlist.getIDPlayList(),Toast.LENGTH_LONG).show();
            GetDataPlaylist(playlist.getIDPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
            setValueInViewAds(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIDTheLoai());
        }
//        if (chude != null && !chude.getTenChuDe().equals("")){
//            setValueInViewAds(chude.getTenChuDe(),chude.getHinhChuDe());
//            GetDataChuDe(chude.getIDChuDe());
//        }
        if (album != null && !album.getTenAlbum().equals("")){
            setValueInViewAds(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIDAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Song>> calllistChude = dataService.GetListSongAlbum(idAlbum);
        calllistChude.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListBaiHatActivity.this,songArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListBaiHatActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idTheLoai) {
        DataService dataService = APIService.getService();
        Call<List<Song>> calllistTheLoai = dataService.GetListSongTheLoai(idTheLoai);
        calllistTheLoai.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListBaiHatActivity.this,songArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListBaiHatActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String IdPlaylist) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callListPlaylist = dataService.GetListSongPlaylist(IdPlaylist);
        callListPlaylist.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListBaiHatActivity.this,songArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListBaiHatActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataAds(String IDADs) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callListSongAds = dataService.GetListSongAds(IDADs);
        callListSongAds.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListBaiHatActivity.this,songArrayList);
                recyclerViewListSong.setLayoutManager(new LinearLayoutManager(ListBaiHatActivity.this));
                recyclerViewListSong.setAdapter(listSongAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInViewAds(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        new GetImageFromUrl().execute(hinh);
        Picasso.get().load(hinh).into(imageView);
    }
    private void setValueInView(String ten,String imgHinhNen,String imgIcon) {
        collapsingToolbarLayout.setTitle(ten);
        new GetImageFromUrl().execute(imgHinhNen);
        Picasso.get().load(imgIcon).into(imageView);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(WHITE);
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        recyclerViewListSong  = findViewById(R.id.recycerviewlistsong);
        toolbar = findViewById(R.id.toolbarListBaiHat);
        imageButton = findViewById(R.id.btnActionAll);
        imageView = findViewById(R.id.imageviewListCaKhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("Ads")){
            quangCao = (QuangCao) intent.getSerializableExtra("Ads");
        }
        if (intent.hasExtra("idplaylist")){
            playlist = (Playlist) intent.getSerializableExtra("idplaylist");
            Log.d("BBB",playlist.getIDPlayList());
        }
        if (intent.hasExtra("idtheloai")){
            theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            Log.d("BBB",theLoai.getIDTheLoai());
        }
//        if (intent.hasExtra("idchude")){
//            chude = (Chude) intent.getSerializableExtra("idchude");
//            Log.d("BBB",chude.getIDChuDe());
//        }
        if (intent.hasExtra("idalbum")){
            album = (Album) intent.getSerializableExtra("idalbum");
            Log.d("BBB",album.getIDAlbum());
        }
    }
    public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... url) {
            String stringUrl = url[0];
            InputStream inputStream;
            try {
                inputStream = new java.net.URL(stringUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        }
    }
}