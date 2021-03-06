package com.example.cool.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.cool.R;
import com.example.cool.base.BaseActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

public class LockActivity extends BaseActivity {

    @Override
    protected void initFlag() {
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //屏蔽系统的锁屏界面，将此activity设置为锁屏界面
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            if (keyguardManager != null) {
                keyguardManager.requestDismissKeyguard(this, null);
            }
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void initView() {
        setRightSlide();
    }

    @Override
    protected void onUserLeaveHint() {
        //用户手动点击Home键或者手动切换app时，此方法会被调用，在这里销毁掉此界面
        finish();
        super.onUserLeaveHint();
    }

    @Override
    public void onBackPressed() {
        //屏蔽返回键
        //super.onBackPressed();
    }

    //右滑解锁
    private void setRightSlide() {
        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .sensitivity(1f)
                .scrimColor(Color.BLACK)
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.5f)
                .build();
        Slidr.attach(this, config);
    }

    @Override
    protected void initData() {

    }
}
