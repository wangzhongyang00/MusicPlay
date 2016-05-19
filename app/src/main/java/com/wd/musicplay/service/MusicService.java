package com.wd.musicplay.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MusicService extends Service {
    MediaPlayer mediaPlayer;
    String TAG = "result";
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: -------------------------------");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ---------------------------------");
        File file = (File) intent.getSerializableExtra("music");
        Log.i(TAG, "onStartCommand: ----file"+file.getAbsolutePath());
        if(file!=null) {
            if(mediaPlayer!=null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            mediaPlayer = MediaPlayer.create(this, Uri.parse(file.getAbsolutePath()));
            Log.i(TAG, "onStartCommand: "+mediaPlayer);
            mediaPlayer.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
