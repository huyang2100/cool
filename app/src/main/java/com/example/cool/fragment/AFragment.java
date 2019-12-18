package com.example.cool.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cool.Base.BaseFragment;
import com.example.cool.Base.MyBaseAdapter;
import com.example.cool.R;
import com.example.cool.holder.AHolder;
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
        MyBaseAdapter myAdapter = new MyBaseAdapter<String, AHolder>(dataList) {

            @Override
            protected void refresh(AHolder holder, String data) {
                holder.tv.setText(data);
            }

            @Override
            protected void bindHolder(AHolder holder, View convertView) {
                holder.tv = convertView.findViewById(R.id.tv);
                holder.tv.setTextSize(22);
            }

            @Override
            protected AHolder createHolder() {
                return new AHolder();
            }

            @Override
            protected View createView() {
                return View.inflate(getContext(), R.layout.item_a,null);
            }
        };

        listView.setAdapter(myAdapter);
        return listView;
    }
}
