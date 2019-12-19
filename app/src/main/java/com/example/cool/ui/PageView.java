package com.example.cool.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.cool.R;
import com.example.cool.base.BaseResponse;
import com.example.cool.http.Api;
import com.example.cool.http.IService.CourtService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class PageView<T> extends FrameLayout {
    //维护的4种view
    private final View loadingView;
    private final View errorView;
    private final View emptyView;
    private View successView;
    private static final String TAG = "PageView";

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
        if (successView != null) {
            addView(successView);
        }

        showPage(null);
    }

    /**
     * 触发网络访问，展现页面
     */
    public void show() {

        Class<CourtService> serviceClazz = getServiceClazz();
        if (serviceClazz != null) {
            Api.enqueue(serviceClazz, getMethodName(), new Callback<BaseResponse<T>>() {
                @Override
                public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                    if (response.code() == 200) {
                        BaseResponse body = response.body();
                        if(body==null){
                            showPage(State.ERROR);
                            return;
                        }
                        ArrayList<T> dataList = body.data;
                        if (dataList.isEmpty()) {
                            showPage(State.EMPTY);
                        } else {
                            refreshSuccessView(dataList);
                            showPage(State.SUCCESS);
                        }
                    } else {
                        showPage(State.ERROR);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
                    showPage(State.ERROR);
                }
            });
        }
    }

    protected abstract void refreshSuccessView(ArrayList<T> dataList);

    protected abstract String getMethodName();

    protected abstract Class<CourtService> getServiceClazz();

    /**
     * 依据枚举状态显示相应页面
     *
     * @param state
     */
    private void showPage(State state) {
        loadingView.setVisibility((state == null || state == State.LOADING) ? VISIBLE : GONE);
        errorView.setVisibility(state == State.ERROR ? VISIBLE : GONE);
        emptyView.setVisibility(state == State.EMPTY ? VISIBLE : GONE);
        if (successView != null) {
            successView.setVisibility(state == State.SUCCESS ? VISIBLE : GONE);
        }
    }

    /**
     * 创建获取网络成功后展现的页面
     *
     * @return
     */
    protected abstract View onCreateSuccessView();

}
