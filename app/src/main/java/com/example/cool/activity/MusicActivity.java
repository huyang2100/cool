package com.example.cool.activity;

import android.widget.ImageButton;

import com.example.cool.R;
import com.example.cool.base.BaseActivity;

public class MusicActivity extends BaseActivity {

    private ImageButton ib;
    private ImageButton seekbar;

    @Override
    protected int getResourceId() {
        return R.layout.activity_music;
    }

    @Override
    protected void initView() {
        ib = findViewById(R.id.ib);
        seekbar = findViewById(R.id.seekbar);
    }

    @Override
    protected void initData() {

    }
}
