package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.build.myapplication.Adapter.ChudeAdapter;
import com.build.myapplication.Model.Chude;
import com.build.myapplication.Model.ChudeAll;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChudeActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllChude;
    Toolbar toolbar;
    ChudeAll chudeAlls;
    ArrayList<Chude> chudeArrayList;
    ChudeAdapter chudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chude);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<ChudeAll> chudeAllCall = dataService.GetChuDeAllAndCurrentDay();
        chudeAllCall.enqueue(new Callback<ChudeAll>() {
            @Override
            public void onResponse(Call<ChudeAll> call, Response<ChudeAll> response) {
                chudeAlls = response.body();
                chudeArrayList = (ArrayList<Chude>) chudeAlls.getChude();
                chudeAdapter = new ChudeAdapter(ChudeActivity.this,chudeArrayList);
                recyclerViewAllChude.setLayoutManager(new GridLayoutManager(ChudeActivity.this,1));
                recyclerViewAllChude.setAdapter(chudeAdapter);
            }

            @Override
            public void onFailure(Call<ChudeAll> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllChude = findViewById(R.id.recyclerViewAllChude);
        toolbar = findViewById(R.id.toolbarAllChude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}