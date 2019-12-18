package com.example.cool.Base;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cool.R;
import com.example.cool.holder.MoreHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class MyBaseAdapter<T, H> extends BaseAdapter {
    private static final int MORE_SIZE = 20;
    private ArrayList<T> dataList;
    private H holder;
    private MoreHolder moreHolder;
    private final int ITEM_NORMAL = 0;
    private final int ITEM_LOAD_MORE = 1;
    private Handler handler = new Handler();

    public MyBaseAdapter(ArrayList<T> dataList) {
        this.dataList = dataList;
    }

    /**
     * 数据list + 加载更多1
     *
     * @return
     */
    @Override
    public int getCount() {
        return dataList.size() + 1;
    }

    /**
     * 一般item + 加载更多item + 其他item
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1 + getOtherViewTypeCount();
    }

    protected int getOtherViewTypeCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        //最后一个item为加载更多item
        if (position == getCount() - 1) {
            return ITEM_LOAD_MORE;
        } else {
            return getOtherItemViewType(position);
        }
    }

    /**
     * 依据position获取对应的其他item的类型
     *
     * @param position
     * @return
     */
    protected int getOtherItemViewType(int position) {
        return ITEM_NORMAL;
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
            if (getItemViewType(position) == ITEM_NORMAL) {
                convertView = createView();
                holder = createHolder();
                bindHolder(holder, convertView);
                convertView.setTag(holder);
            } else {
                convertView = View.inflate(parent.getContext(), R.layout.load_more, null);
                moreHolder = new MoreHolder();
                moreHolder.bind(convertView);
                moreHolder.btn_load_retry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadMore();
                    }
                });
                convertView.setTag(moreHolder);
            }
        } else {
            if (getItemViewType(position) == ITEM_NORMAL) {
                holder = (H) convertView.getTag();
            } else {
                moreHolder = (MoreHolder) convertView.getTag();
            }
        }

        if (getItemViewType(position) == ITEM_NORMAL) {
            T data = getItem(position);
            refresh(holder, data);
        } else if (getItemViewType(position) == ITEM_LOAD_MORE) {
            if(moreHolder.hasMoreData()){
                //loadmore可见，触发加载更多网络请求
                loadMore();
            }
        }
        return convertView;
    }

    private void loadMore() {
        moreHolder.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<T> moreList = onLoadMore();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (moreList == null) {
                            moreHolder.showRetry();
                        } else {
                            if (moreList.size() < MORE_SIZE) {
                                moreHolder.showEmpty();
                            } else {
                                moreHolder.showLoading();
                            }

                            dataList.addAll(moreList);
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        }).start();
    }

    protected abstract List<T> onLoadMore();

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
