package com.example.splashactivity.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.splashactivity.R;
import com.example.splashactivity.model.Music;
import com.example.splashactivity.service.MyMusicService;
import com.example.splashactivity.util.SharedUtil;
import com.squareup.picasso.Picasso;

public class PlayActivity extends AppCompatActivity {
    private ImageView stopView;
    private MediaPlayer mediaPlayer;
    private FloatingActionButton fab;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra("music")) {
            final Music music = intent.getExtras().getParcelable("music");
            ImageView poster = findViewById(R.id.posterView);
            Picasso.get().load(music.getImg_url()).into(poster);

            fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i( "onClick: ", SharedUtil.getInShared(PlayActivity.this)+"");
                    if(!SharedUtil.getInShared(PlayActivity.this)) {
                        i.putExtra("music", music.getSound_url());
                        i.putExtra("btn", "play");
                        startService(i);
                        SharedUtil.saveInShared(PlayActivity.this,true);
                        SharedUtil.saveSongShared(PlayActivity.this,music.getName());
                        stopView.setImageResource(R.drawable.ic_stop);
                        fab.setImageResource(R.drawable.ic_stop);
                    }else{
                        stopService(i);
                        SharedUtil.saveInShared(PlayActivity.this,false);
                        SharedUtil.saveSongShared(PlayActivity.this,"");
                        stopView.setImageResource(R.drawable.ic_play2);
                        fab.setImageResource(R.drawable.ic_play2);
                    }


                }
            });


            ImageView forwardView = findViewById(R.id.imageView4);
            stopView = findViewById(R.id.imageView5);
            ImageView backwardView = findViewById(R.id.imageView6);
            stopView.setImageResource(R.drawable.ic_play2);


            forwardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i.putExtra("btn","forward");
                    startService(i);
                }
            });


            stopView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SharedUtil.getInShared(PlayActivity.this)) {
                        stopService(i);
                        SharedUtil.saveInShared(PlayActivity.this,false);
                        SharedUtil.saveSongShared(PlayActivity.this,music.getName());
                        stopView.setImageResource(R.drawable.ic_play2);
                        fab.setImageResource(R.drawable.ic_play2);
                    } else {
                        i.putExtra("btn","play");
                        i.putExtra("music", music.getSound_url());
                        startService(i);
                        SharedUtil.saveInShared(PlayActivity.this,true);
                        SharedUtil.saveSongShared(PlayActivity.this,music.getName());
                        stopView.setImageResource(R.drawable.ic_stop);
                        fab.setImageResource(R.drawable.ic_stop);

                    }

                }

            });

            backwardView.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                    i.putExtra("btn","back");
                    startService(i);

//
                }
            });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        i = new Intent(PlayActivity.this, MyMusicService.class);

        boolean isPlay = SharedUtil.getInShared(this);
        if(isPlay){
            fab.setImageResource(R.drawable.ic_stop);
            stopView.setImageResource(R.drawable.ic_stop);

        }else{
            fab.setImageResource(R.drawable.ic_play2);
            stopView.setImageResource(R.drawable.ic_play2);


        }
    }
}