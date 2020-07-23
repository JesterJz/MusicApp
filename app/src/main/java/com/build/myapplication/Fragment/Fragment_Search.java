package com.build.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.build.myapplication.Adapter.SearchAdapter;
import com.build.myapplication.Model.Song;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Search extends Fragment {
    View view;
    Toolbar toolbarSearchSong;
    RecyclerView recyclerViewSearchSong;
    TextView textViewNull;
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbarSearchSong = view.findViewById(R.id.toolbarSearchSong);
        recyclerViewSearchSong = view.findViewById(R.id.recyclerViewSearchSong);
        textViewNull = view.findViewById(R.id.textViewNull);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarSearchSong);
        toolbarSearchSong.setTitle("");
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchDataService(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchDataService(String query) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callListSongSearch = dataService.GetListSongSearch(query);
        callListSongSearch.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                if (songArrayList.size() > 0) {
                    searchAdapter = new SearchAdapter(getActivity(),songArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearchSong.setLayoutManager(linearLayoutManager);
                    recyclerViewSearchSong.setAdapter(searchAdapter);
                    textViewNull.setVisibility(View.GONE);
                    recyclerViewSearchSong.setVisibility(View.VISIBLE);
                }else {
                    recyclerViewSearchSong.setVisibility(View.GONE);
                    textViewNull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
