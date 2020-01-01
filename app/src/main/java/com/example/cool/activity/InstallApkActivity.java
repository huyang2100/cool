package com.example.cool.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.cool.BuildConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.cool.R;

import java.io.File;
import java.net.URI;

public class InstallApkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_apk);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "package: "+InstallApkActivity.this.getPackageName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(Intent.ACTION_VIEW);
                String apkPath = "/mnt/sdcard/Download/scheduleBj20190820.apk";

                Uri apkUri;
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    apkUri = Uri.parse("file://" + apkPath);
                } else {
                    i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    File file = new File(apkPath);
                    apkUri = FileProvider.getUriForFile(InstallApkActivity.this, BuildConfig.APPLICATION_ID + ".provider", file);
                }

                i.setDataAndType(apkUri, "application/vnd.android.package-archive");
                startActivity(i);
            }
        });
    }

}
