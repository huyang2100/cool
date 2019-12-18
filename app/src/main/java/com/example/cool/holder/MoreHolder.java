package com.example.cool.holder;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cool.R;

public class MoreHolder {
    public LinearLayout rl_loading;
    public Button btn_load_retry;

    public void bind(View convertView) {
        rl_loading = convertView.findViewById(R.id.rl_loading);
        btn_load_retry = convertView.findViewById(R.id.btn_load_retry);
    }

    public boolean hasMoreData() {
        return rl_loading.getVisibility() != View.GONE;
    }

    public void showLoading() {
        btn_load_retry.setVisibility(View.GONE);
        rl_loading.setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        btn_load_retry.setVisibility(View.GONE);
        rl_loading.setVisibility(View.GONE);
    }

    public void showRetry() {
        btn_load_retry.setVisibility(View.VISIBLE);
        rl_loading.setVisibility(View.GONE);
    }
}
