package com.example.cool.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cool.ui.PageView;

public abstract class BaseFragment extends Fragment {

    private PageView pageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        pageView = new PageView(getContext()) {
            @Override
            protected View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }

            @Override
            protected PageView.State onLoad() {
                return BaseFragment.this.onLoad();
            }
        };
        return pageView;
    }

    protected abstract PageView.State onLoad();

    protected abstract View onCreateSuccessView();

    @Override
    public void onStart() {
        super.onStart();
        pageView.show();
    }
}
