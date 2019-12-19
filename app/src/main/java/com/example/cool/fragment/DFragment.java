package com.example.cool.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.cool.base.BaseFragment;
import com.example.cool.http.IService.CourtService;

import java.util.ArrayList;

public class DFragment extends BaseFragment {

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
        TextView textView = new TextView(getContext());
        textView.setText("成功！");
        textView.setTextSize(100);
        return textView;
    }
}
