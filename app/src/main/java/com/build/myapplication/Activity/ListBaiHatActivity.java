package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.R;

public class ListBaiHatActivity extends AppCompatActivity {
    QuangCao quangCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);
        DataIntent();
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
}