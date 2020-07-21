package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.Model.Song;
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

public class ListBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageButton imageButton;
    QuangCao quangCao;
    ImageView imageView;
    ArrayList<Song> songArrayList;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        DataIntent();
        AnhXa();
        init();
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")){
            setValueInView(quangCao.getTenBaiHat(),quangCao.getHinhBaiHat());
            GetDataAds(quangCao.getIDAds());
        }
    }

    private void GetDataAds(String IDADs) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callListSongAds = dataService.GetListSongAds(IDADs);
        callListSongAds.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                Log.d("BBB",songArrayList.get(0).getTenBaiHat());
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten,String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        new GetImageFromUrl().execute(hinh);
//        try {
//            conn.connect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            URL url = new URL(hinh);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
////            InputStream input = null;
////            connection.setRequestMethod("GET");
////            connection.connect();
//
////            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
////                input = connection.getInputStream();
////            }
//            Bitmap bitmap = BitmapFactory.decodeStream(input);
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
//            collapsingToolbarLayout.setBackground(bitmapDrawable);
//        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Picasso.get().load(hinh).into(imageView);
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
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        recyclerView  = findViewById(R.id.recycerviewlistsong);
        toolbar = findViewById(R.id.toolbarListBaiHat);
        imageButton = findViewById(R.id.btnActionAll);
        imageView = findViewById(R.id.imageviewListCaKhuc);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("Ads")){
            if (intent.hasExtra("Ads")){
                quangCao = (QuangCao) intent.getSerializableExtra("Ads");
                Log.d("BBB",quangCao.getTenBaiHat());
            }
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