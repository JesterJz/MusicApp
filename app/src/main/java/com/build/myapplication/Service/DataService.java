package com.build.myapplication.Service;

import com.build.myapplication.Model.Album;
import com.build.myapplication.Model.ChudeAll;
import com.build.myapplication.Model.Playlist;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.Model.Song;
import com.build.myapplication.Model.SongLove;
import com.build.myapplication.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//sent and receiver method tuong tac
public interface DataService {

    @GET("songbaner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlist_current_day.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("TheLoai_current_day.php")
    Call<List<TheLoai>> GetTheLoaiCurrentDay();

    @GET("theloai_all.php")
    Call<List<TheLoai>> GetTheLoaiAll();

    @GET("Chude_current_day.php")
    Call<ChudeAll> GetChuDeAllAndCurrentDay();

    @GET("Albumhot.php")
    Call<List<Album>> GetAlBumHot();

    @GET("Album.php")
    Call<List<Album>> GetAlBum();

    @GET("SongLove.php")
    Call<List<Song>> GetSongLove();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongAds(@Field("IDAds") String IDAds);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongPlaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongTheLoai(@Field("idtheloai") String idtheloai);

//    @FormUrlEncoded
//    @POST("listsong.php")
//    Call<List<Song>> GetListSongChuDe(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("playlist_theo_chude.php")
    Call<List<Playlist>> GetListTheLoaiByChude(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Song>> GetListSongAlbum(@Field("idalbum") String idbum);
}
