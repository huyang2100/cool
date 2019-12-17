package com.example.cool.ui;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.cool.R;


public abstract class PageView extends FrameLayout {
    //维护的4种view
    private final View loadingView;
    private final View errorView;
    private final View emptyView;
    private View successView;

    private Handler handler = new Handler();

    /**
     * 访问网络状态枚举类
     */
    public enum State {
        LOADING(0), ERROR(1), EMPTY(2), SUCCESS(3);
        private final int state;

        State(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

    public PageView(@NonNull Context context) {
        super(context);
        loadingView = View.inflate(context, R.layout.view_loading, null);
        addView(loadingView);
        errorView = View.inflate(context, R.layout.view_error, null);
        addView(errorView);
        emptyView = View.inflate(context, R.layout.view_empty, null);
        addView(emptyView);
        successView = onCreateSuccessView();
        if(successView != null){
            addView(successView);
        }

        showPage(null);
    }

    /**
     * 触发网络访问，展现页面
     */
    public void show() {
        //开线程，访问网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程中同步访问网络
                final State state = onLoad();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        showPage(state);
                    }
                });

            }
        }).start();
    }

    /**
     * 依据枚举状态显示相应页面
     *
     * @param state
     */
    private void showPage(State state) {
        loadingView.setVisibility((state == null || state == State.LOADING) ? VISIBLE : GONE);
        errorView.setVisibility(state == State.ERROR ? VISIBLE : GONE);
        emptyView.setVisibility(state == State.EMPTY ? VISIBLE : GONE);
        if(successView != null){
            successView.setVisibility(state == State.SUCCESS ? VISIBLE : GONE);
        }
    }

    /**
     * 创建获取网络成功后展现的页面
     *
     * @return
     */
    protected abstract View onCreateSuccessView();

    /**
     * 访问网络，加载数据
     *
     * @return
     */
    protected abstract State onLoad();
}
