package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.build.myapplication.Adapter.TheLoaiAdapter;
import com.build.myapplication.Model.TheLoai;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TheLoaiActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllTheLoai;
    Toolbar toolbar;
    ArrayList<TheLoai> theLoaiArrayList;
    TheLoaiAdapter theLoaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        AnhXa();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> calllistTheLoai = dataService.GetTheLoaiAll();
        calllistTheLoai.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                theLoaiAdapter = new TheLoaiAdapter(TheLoaiActivity.this,theLoaiArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TheLoaiActivity.this);
                recyclerViewAllTheLoai.setLayoutManager(linearLayoutManager);
                recyclerViewAllTheLoai.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        recyclerViewAllTheLoai = findViewById(R.id.recyclerViewAllTheLoai);
        toolbar = findViewById(R.id.toolbarAllTheloai);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thể Loại");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}