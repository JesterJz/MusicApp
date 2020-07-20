package com.build.myapplication.Service;

import com.build.myapplication.Model.Album;
import com.build.myapplication.Model.ChudeAll;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.Model.SongLove;
import com.build.myapplication.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//sent and receiver method tuong tac
public interface DataService {

    @GET("songbaner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlist_current_day.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("TheLoai_current_day.php")
    Call<List<TheLoai>> GetTheLoaiCurrentDay();

    @GET("Chude_current_day.php")
    Call<ChudeAll> GetChuDeAllAndCurrentDay();

    @GET("Albumhot.php")
    Call<List<Album>> GetAlBumHot();

    @GET("Album.php")
    Call<List<Album>> GetAlBum();

    @GET("SongLove.php")
    Call<List<SongLove>> GetSongLove();
}
