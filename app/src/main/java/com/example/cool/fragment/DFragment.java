package com.example.cool.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.cool.Base.BaseFragment;
import com.example.cool.ui.PageView;

public class DFragment extends BaseFragment {
    @Override
    protected PageView.State onLoad() {
        return PageView.State.SUCCESS;
    }

    @Override
    protected View onCreateSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText("成功！");
        textView.setTextSize(100);
        return textView;
    }
}
