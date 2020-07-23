package com.build.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Adapter.SongLoveAdapter;
import com.build.myapplication.Model.Song;
import com.build.myapplication.Model.SongLove;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_SongLove extends Fragment {
    View view;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_songlove,container,false);
        AnhXa();
        GetData();
        return view;

    }

    private void AnhXa() {
        recyclerView = view.findViewById(R.id.recyclerViewSongLove);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Song>> listSongLove = dataService.GetSongLove();
        listSongLove.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songLoveArrayList = (ArrayList<Song>) response.body();
                Log.d("BBB",songLoveArrayList.get(0).getHinhBaiHat());
                SongLoveAdapter songLoveAdapter = new SongLoveAdapter(getActivity(),songLoveArrayList);
                LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(songLoveAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
