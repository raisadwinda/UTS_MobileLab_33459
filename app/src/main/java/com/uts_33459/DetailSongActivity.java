package com.uts_33459;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetailSongActivity extends AppCompatActivity {
    ActionBar actionBar;
    ImageView img_albumCover_detail;
    TextView tv_title_detail;
    TextView tv_singer_detail;
    ImageButton imgbtn_play, imgbtn_next;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_song);
        img_albumCover_detail = findViewById(R.id.image_albumCover_detail);
        tv_title_detail = findViewById(R.id.text_title_detail);
        tv_singer_detail = findViewById(R.id.text_singer_detail);
        imgbtn_play = findViewById(R.id.button_play);
        imgbtn_next = findViewById(R.id.button_next);
        seekBar = findViewById(R.id.seekBar);
        actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        Bundle bundle = getIntent().getExtras();
        Integer imgCoverAlbum = bundle.getInt("album cover");
        String strTitle = bundle.getString("song title");
        String strSinger = bundle.getString("song singer");

        //set album cover
        img_albumCover_detail.setImageResource(imgCoverAlbum);
        //set song title
        tv_title_detail.setText(strTitle);
        //set song singer
        tv_singer_detail.setText(strSinger);

        tv_title_detail.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv_title_detail.setSelected(true);

        mediaPlayer=new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beautiful_day);
        playMediaPlayer();

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

//        imgbtn_play.setBackgroundResource(R.drawable.ic_play);

        imgbtn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgbtn_play.setBackgroundResource(R.drawable.ic_play);
                }else{
                    //mediaPlayer.release();
                    mediaPlayer.start();
                    updateSeekBar UpdateSeekBar = new updateSeekBar();
                    handler.post(UpdateSeekBar);
                    imgbtn_play.setBackgroundResource(R.drawable.ic_pause);
                }
            }
        });
    }

    private void playMediaPlayer() {
        imgbtn_play.setBackgroundResource(R.drawable.ic_pause);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else{
            mediaPlayer.start();
            updateSeekBar UpdateSeekBar = new updateSeekBar();
            handler.post(UpdateSeekBar);
        }
    }

    public class updateSeekBar implements Runnable {
        @Override
        public void run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this,100);
        }
    }
}