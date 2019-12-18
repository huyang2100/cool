package com.example.cool.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cool.Base.BaseFragment;
import com.example.cool.R;
import com.example.cool.ui.PageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment {

    @Override
    protected PageView.State onLoad() {
        return PageView.State.SUCCESS;
    }

    @Override
    protected View onCreateSuccessView() {
        ListView listView = new ListView(getContext());
        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            dataList.add(i + "");
        }
        MyAdapter myAdapter = new MyAdapter(dataList);

        listView.setAdapter(myAdapter);
        return listView;
    }

    class MyAdapter extends BaseAdapter {

        private final ArrayList<String> dataList;
        private ViewHolder holder;

        public MyAdapter(ArrayList<String> dataList) {
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public String getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_a, null);
                holder = new ViewHolder();
                holder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String data = getItem(position);
            holder.tv.setText(data);
            return convertView;
        }

        class ViewHolder {
            public TextView tv;
        }
    }

}
