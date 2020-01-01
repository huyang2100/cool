package com.example.cool;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.example.cool.service.MusicService;

public class MApplication extends Application {
    private static final String TAG = "MApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        startMusicService();
    }

    private void startMusicService() {
        Log.d(TAG, "startMusicService: ");
        Intent intent = new Intent(getApplicationContext(), MusicService.class);
        startService(intent);
    }
}
