package com.example.eyepetizer.view.activity;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.eyepetizer.R;
import com.example.eyepetizer.bean.HotBean;

import java.util.ArrayList;

public class VedioDisplay extends AppCompatActivity {
    ImageView head;
    TextView name;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final VideoView videoView = findViewById(R.id.video);
        head = findViewById(R.id.video_head);
        name = findViewById(R.id.video_name);
        title = findViewById(R.id.video_title);
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name");
        String head1 = intent.getStringExtra("head");
        String description = intent.getStringExtra("description");
        String url = intent.getStringExtra("url");
        title.setText(description);
        name.setText(name1);
        Glide.with(this).load(head1).into(head);
        videoView.setVideoPath(url);
        videoView.setMediaController(new MediaController(this));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

    }
}
