package com.example.cool.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;

import com.example.cool.Base.BaseFragment;
import com.example.cool.ui.PageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment {

    @Override
    protected PageView.State onLoad() {
        return PageView.State.LOADING;
    }

    @Override
    protected View onCreateSuccessView() {
        return null;
    }

}
