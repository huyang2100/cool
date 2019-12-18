package com.example.cool.Base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        setContentView(getResourceId());
        initView();
        initData();
    }

    protected abstract int getResourceId();

    protected abstract void initView();

    protected abstract void initData();
}
