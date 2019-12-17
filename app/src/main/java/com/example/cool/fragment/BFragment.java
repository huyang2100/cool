package com.example.cool.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cool.Base.BaseFragment;
import com.example.cool.R;
import com.example.cool.ui.PageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends BaseFragment {


    @Override
    protected PageView.State onLoad() {
        return PageView.State.ERROR;
    }

    @Override
    protected View onCreateSuccessView() {
        return null;
    }
}
