package com.example.cool.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;

import com.example.cool.base.BaseFragment;
import com.example.cool.http.IService.CourtService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends BaseFragment {


    @Override
    protected void refreshSuccessView(ArrayList dataList) {

    }

    @Override
    protected String getMethodName() {
        return null;
    }

    @Override
    protected Class<CourtService> getServiceClazz() {
        return null;
    }

    @Override
    protected View onCreateSuccessView() {
        return null;
    }
}
