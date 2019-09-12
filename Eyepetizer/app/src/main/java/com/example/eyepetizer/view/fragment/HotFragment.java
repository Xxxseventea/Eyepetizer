package com.example.eyepetizer.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.HotRecycleViewAdapter;
import com.example.eyepetizer.bean.HotBean;
import com.example.eyepetizer.bean.HttpBean;
import com.example.eyepetizer.db.SearchListDbOperation;
import com.example.eyepetizer.interfaces.CallBack;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.util.HttpUtil;
import com.example.eyepetizer.util.JsonUtil;
import com.example.eyepetizer.view.activity.VedioDisplay;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.eyepetizer.db.DatabaseHelper.NAME;

public class HotFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<HotBean> hotBeans;
    HttpBean httpBean;
    HotRecycleViewAdapter hotRecycleViewAdapter;
    SearchListDbOperation searchListDbOperation;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    hotRecycleViewAdapter = new HotRecycleViewAdapter(getContext(), hotBeans);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(hotRecycleViewAdapter);
                    hotRecycleViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int pos, String url, String head, String name, String description, String title) {
                            Intent intent = new Intent(getActivity(), VedioDisplay.class);
                            intent.putExtra("url", url);
                            intent.putExtra("head", head);
                            intent.putExtra("name", name);
                            intent.putExtra("description", description);
                            startActivity(intent);
                            searchListDbOperation = new SearchListDbOperation(getContext(), "History");
                            searchListDbOperation.check();
                            searchListDbOperation.add(head, name, title);
                        }
                    });
            }
        }
    };
    Message message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hot_main, container, false);
        recyclerView = view.findViewById(R.id.hot_recyclerview);
        hotBeans = new ArrayList<>();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpBean = new HttpBean.Builder()
                            .setMap(null)
                            .setMethod("GET")
                            .setUrl("http://baobab.kaiyanapp.com/api/v4/categories/detail/index?id=36")
                            .build();
                    HttpUtil.GetHttpString(httpBean, new CallBack() {
                        @Override
                        public void finish(final String request) {
                            JsonUtil.getInstance().HotJson(request, hotBeans, hotRecycleViewAdapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);

            }
        }).start();
        return view;
    }


}