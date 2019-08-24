package com.example.splashactivity.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyMusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String btn = intent.getExtras().getString("btn","");
        if(btn.equals("play")) {
            String url = intent.getExtras().getString("music");
            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(url));
            mediaPlayer.start();
        }else if(btn.equals("back")){
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                if (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition() > 5000) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                } else {
                    mediaPlayer.stop();
                }
            }

        }else{
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                if (mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition() > 5000) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                } else {
                    mediaPlayer.stop();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }


}
