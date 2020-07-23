package com.build.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.build.myapplication.Adapter.ViewPagerPlayMusicAdapter;
import com.build.myapplication.Fragment.Fragment_list_song_play;
import com.build.myapplication.Fragment.Fragment_music_disc;
import com.build.myapplication.Model.Song;
import com.build.myapplication.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar seekBar;
    ImageButton imageButtonPlay, imageButtonRepeat, imageButtonNext, imageButtonPre, imageButtonRandom;
    ViewPager viewPager;
    public static ArrayList<Song> songArrayList = new ArrayList<>();
    public static ViewPagerPlayMusicAdapter viewPagerPlayMusicAdapter;
    Fragment_music_disc fragment_music_disc;
    Fragment_list_song_play fragment_list_song_play;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
//        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(threadPolicy);
        getDataIntent();
        init();
        evenClick();
    }

    private void evenClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewPagerPlayMusicAdapter.getItem(1) != null) {
                    if (songArrayList.size() > 0) {
                        fragment_music_disc.Playnhac(songArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imageButtonPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imageButtonRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imageButtonRepeat.setImageResource(R.drawable.iconsyned);
                        imageButtonRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imageButtonRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imageButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtonRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtonRandom.setImageResource(R.drawable.iconshuffled);
                        imageButtonRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imageButtonRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imageButtonRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imageButtonPlay.setImageResource(R.drawable.iconplay);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position >= songArrayList.size()) {
                            position = 0;
                        }
                        new PlayMusic().execute(songArrayList.get(position).getLinkBaiHat());
                        fragment_music_disc.Playnhac(songArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonNext.setClickable(false);
                imageButtonRepeat.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonNext.setClickable(true);
                        imageButtonRepeat.setClickable(true);
                    }
                }, 5000);
            }
        });
        imageButtonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (songArrayList.size())) {
                        imageButtonPlay.setImageResource(R.drawable.iconplay);
                        position--;
                        if (position < 0) {
                            position = songArrayList.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMusic().execute(songArrayList.get(position).getLinkBaiHat());
                        fragment_music_disc.Playnhac(songArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imageButtonNext.setClickable(false);
                imageButtonRepeat.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonNext.setClickable(true);
                        imageButtonRepeat.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarPlayMusic);
        txtTimeSong = findViewById(R.id.textView_time_song);
        txtTotalTimeSong = findViewById(R.id.textviewTotal_time_song);
        seekBar = findViewById(R.id.seebarPlayMusic);
        imageButtonRandom = findViewById(R.id.imagebuttonsuffle);
        imageButtonPre = findViewById(R.id.imagebuttonpreview);
        imageButtonPlay = findViewById(R.id.imagebuttonplay);
        imageButtonNext = findViewById(R.id.imagebutton_next);
        imageButtonRepeat = findViewById(R.id.imagebuttonrepeat);
        viewPager = findViewById(R.id.viewPager_playMusic);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                songArrayList.clear();
               // mediaPlayer.stop();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        fragment_music_disc = new Fragment_music_disc();
        fragment_list_song_play = new Fragment_list_song_play();
        viewPagerPlayMusicAdapter = new ViewPagerPlayMusicAdapter(getSupportFragmentManager());
        viewPagerPlayMusicAdapter.AddFragment(fragment_list_song_play);
        viewPagerPlayMusicAdapter.AddFragment(fragment_music_disc);
        viewPager.setAdapter(viewPagerPlayMusicAdapter);
        fragment_music_disc = (Fragment_music_disc) viewPagerPlayMusicAdapter.getItem(1);
        if (songArrayList.size() > 0) {
            getSupportActionBar().setTitle(songArrayList.get(0).getTenBaiHat());
            new PlayMusic().execute(songArrayList.get(0).getLinkBaiHat());
            imageButtonPlay.setImageResource(R.drawable.iconpause);
        }
    }

    private void getDataIntent() {
        Intent intent = getIntent();
//        mediaPlayer.stop();
        songArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                Song song = intent.getParcelableExtra("song");
                songArrayList.add(song);
            }
            if (intent.hasExtra("songs")) {
                ArrayList<Song> arrayListExtra = intent.getParcelableArrayListExtra("songs");
                songArrayList = arrayListExtra;
            }
        }

    }

    class PlayMusic extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.reset();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                handler.postDelayed(this, 200);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        next = true;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }, 200);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (songArrayList.size())) {
                        imageButtonPlay.setImageResource(R.drawable.iconplay);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = songArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(songArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position >= songArrayList.size()) {
                            position = 0;
                        }
                        new PlayMusic().execute(songArrayList.get(position).getLinkBaiHat());
                        fragment_music_disc.Playnhac(songArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(songArrayList.get(position).getTenBaiHat());
                        imageButtonPlay.setImageResource(R.drawable.iconpause);
                    }
                    imageButtonNext.setClickable(false);
                    imageButtonRepeat.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonNext.setClickable(true);
                            imageButtonRepeat.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}