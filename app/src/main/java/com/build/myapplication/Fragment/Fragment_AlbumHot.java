package com.build.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Fragment_AlbumHot extends Fragment {
    AlbumHotAdapter albumHotAdapter;
    RecyclerView recyclerViewAlbumHot;
    TextView textView;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_albumhot,container,false);
        AnhXa();
        GetData();
        return view;
    }


    private void AnhXa() {
        recyclerViewAlbumHot = view.findViewById(R.id.recycerViewAlbumhot);
        textView = view.findViewById(R.id.moreAlbumhot);
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> listCall = dataService.GetAlBumHot();
        listCall.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> arrayAlbumHot = (ArrayList<Album>) response.body();
                albumHotAdapter = new AlbumHotAdapter(getActivity(),arrayAlbumHot);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerViewAlbumHot.setLayoutManager(linearLayoutManager);
                recyclerViewAlbumHot.setAdapter(albumHotAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.d("BBB","404");
            }
        });
    }
}
