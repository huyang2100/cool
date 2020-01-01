package com.example.cool.activity;

import android.util.TimeUtils;

import com.example.cool.utils.LogUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import static org.junit.Assert.*;

public class RxJavaTest {
    private static final String TAG = "RxJavaTest";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRxJava() {
        //创建被观察者
        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("hello: 1");
            emitter.onNext("hello: 2");
            emitter.onNext("hello: 3");
            emitter.onNext("hello: 4");
            emitter.onNext("hello: 5");
            emitter.onComplete();
        });

        //创建观察者
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe: 开始监听..." + d);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: 监听出错" + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: 监听完成");
            }
        };

        //产生订阅关系
        observable.subscribe(observer);

    }

    @Test
    public void testJust() {
        Observable.just("google", "apple", "amzon", "tencent").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe: 开始");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: 完成");
            }
        });
    }

    @Test
    public void testTimer() {
        Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> System.out.println("testTimer: " + aLong + " " + System.currentTimeMillis()));
    }

    @Test
    public void testInterval() {
        Observable.interval(2, TimeUnit.SECONDS).subscribe(aLong -> System.out.println("testInterval: " + aLong));
    }

    @Test
    public void testFilter() {
        Observable.just(1, 2, 3, 4, 5).filter(aInteger -> aInteger < 4).subscribe(aInteger -> {
            System.out.println("testFilter: "+aInteger);
        });
    }

    @Test
    public void testOfType(){
        Observable.just(1,2,"hello",200,"word").ofType(String.class).subscribe(aString -> System.out.println("testOfType: "+aString));
    }

    @Test
    public void testSkip(){
        Observable.just(1,2,3,4,5,6).skip(2).subscribe(aInteger -> System.out.println("testSkip: "+aInteger));
    }

    @Test
    public void testDistinct(){
        Observable.just(1,2,3,2,3,4,5,1,6).distinct().subscribe(aInteger -> System.out.println("testSkip: "+aInteger));
    }

    @Test
    public void testdistinctUntilChanged(){
        Observable.just(1,2,3,3,4,5,1,6).distinctUntilChanged().subscribe(aInteger -> System.out.println("testSkip: "+aInteger));
    }

    @Test
    public void testTake(){
        Observable.just(1,2,3,3,4,5,1,6).take(2).subscribe(aInteger -> System.out.println("testSkip: "+aInteger));
    }


}