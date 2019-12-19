package com.example.cool.fragment;


import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ListView;

import com.example.cool.base.BaseFragment;
import com.example.cool.base.BaseResponse;
import com.example.cool.base.MyBaseAdapter;
import com.example.cool.R;
import com.example.cool.holder.AHolder;
import com.example.cool.http.IService.CourtService;
import com.example.cool.http.response.CourtResponse;
import com.example.cool.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment<CourtResponse.Court> {

    private ListView listView;


    @Override
    protected void refreshSuccessView(ArrayList<CourtResponse.Court> dataList) {
        MyBaseAdapter myAdapter = new MyBaseAdapter<CourtResponse.Court, AHolder>(dataList) {

            @Override
            protected List<CourtResponse.Court> onLoadMore() {
                //TODO del
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<CourtResponse.Court> list = new ArrayList<>();

                CourtResponse.Court court;
                for (int i = 0; i < 20; i++) {
                    court = new CourtResponse.Court();
                    court.name = "法院：" + i;
                    list.add(court);
                }
                return list;
            }

            @Override
            protected void refresh(AHolder holder, CourtResponse.Court data) {
                holder.tv.setText(data.name);
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
                return View.inflate(getContext(), R.layout.item_a, null);
            }
        };

        listView.setAdapter(myAdapter);
        LogUtil.i(TAG, "refreshSuccessView: " + dataList.toString());
    }

    @Override
    protected String getMethodName() {
        return "getCourts";
    }

    @Override
    protected Class<CourtService> getServiceClazz() {
        return CourtService.class;
    }

    @Override
    protected View onCreateSuccessView() {
        listView = new ListView(getContext());
        return listView;
    }
}
