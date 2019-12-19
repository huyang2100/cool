package com.example.cool.base;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cool.R;
import com.example.cool.holder.MoreHolder;
import com.example.cool.http.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Api.enqueue(getServiceClass(), getMethodName(), new Callback<BaseResponse<T>>() {
            @Override
            public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
                if(response.code() == 200){
                    BaseResponse<T> body = response.body();
                    if(body == null){
                        moreHolder.showRetry();
                        return;
                    }

                    ArrayList<T> moreList = body.data;
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
                }else{
                    moreHolder.showRetry();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<T>> call, Throwable t) {

            }
        });
    }

    protected abstract String getMethodName();

    protected abstract Class getServiceClass();

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
