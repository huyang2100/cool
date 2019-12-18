package com.example.cool.Base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cool.R;

import java.util.ArrayList;

public abstract class MyBaseAdapter<T, H> extends BaseAdapter {
    private ArrayList<T> dataList;
    private H holder;

    public MyBaseAdapter(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = createView();
            holder = createHolder();
            bindHolder(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (H) convertView.getTag();
        }
        T data = getItem(position);
        refresh(holder, data);
        return convertView;
    }

    /**
     * 刷新holder
     *
     * @param holder
     * @param data
     */
    protected abstract void refresh(H holder, T data);

    /**
     * 绑定holder
     *
     * @param holder
     * @param convertView
     */
    protected abstract void bindHolder(H holder, View convertView);

    /**
     * 创建Holder
     *
     * @return
     */
    protected abstract H createHolder();

    /**
     * 创建ItemView
     *
     * @return
     */
    protected abstract View createView();
}
