package com.example.cool.activity;

import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.example.cool.base.BaseActivity;
import com.example.cool.R;
import com.example.cool.fragment.AFragment;
import com.example.cool.fragment.BFragment;
import com.example.cool.fragment.CFragment;
import com.example.cool.fragment.DFragment;
import com.example.cool.utils.LogUtil;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private FragmentPagerItemAdapter adapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        viewPagerTab = findViewById(R.id.viewpagertab);
    }

    @Override
    protected void initData() {
        String[] titles = getResources().getStringArray(R.array.titles);

        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], AFragment.class)
                .add(titles[1], BFragment.class)
                .add(titles[2], CFragment.class)
                .add(titles[3], DFragment.class)
                .add(titles[4], AFragment.class)
                .add(titles[5], BFragment.class)
                .create());

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);

        //延时10秒，开始一个任务
        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong->{
            MainActivity.this.runOnUiThread(()->{
                Toast.makeText(this, "hello: timer", Toast.LENGTH_SHORT).show();
            });
        });
    }
}
