package com.example.cool.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cool.http.IService.CourtService;
import com.example.cool.ui.PageView;

import java.util.ArrayList;

public abstract class BaseFragment<T> extends Fragment {

    private PageView pageView;
    protected String TAG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        pageView = new PageView<T>(getContext()) {
            @Override
            protected void refreshSuccessView(ArrayList<T> dataList) {
                BaseFragment.this.refreshSuccessView(dataList);
            }

            @Override
            protected String getMethodName() {
                return BaseFragment.this.getMethodName();
            }

            @Override
            protected Class<CourtService> getServiceClazz() {
                return BaseFragment.this.getServiceClazz();
            }

            @Override
            protected View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }
        };
        return pageView;
    }

    protected abstract void refreshSuccessView(ArrayList<T> dataList);

    protected abstract String getMethodName();

    protected abstract Class<CourtService> getServiceClazz();

    protected abstract View onCreateSuccessView();

    @Override
    public void onStart() {
        super.onStart();
        pageView.show();
    }
}
