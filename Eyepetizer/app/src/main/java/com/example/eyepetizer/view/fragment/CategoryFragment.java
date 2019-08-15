package com.example.eyepetizer.view.fragment;

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

import com.example.eyepetizer.interfaces.CallBack;
import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.CategoryRecyclerViewAdapter;
import com.example.eyepetizer.bean.CategoryBean;
import com.example.eyepetizer.bean.HttpBean;
import com.example.eyepetizer.util.HttpUtil;
import com.example.eyepetizer.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    CategoryRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    HttpBean httpBean;
    ArrayList<CategoryBean> data;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter = new CategoryRecyclerViewAdapter(getContext(),data);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
            }
        }
    };
    Message message;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_main,container,false);
        recyclerView = view.findViewById(R.id.category_rv);
        data = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //网络请求
                    httpBean = new HttpBean.Builder()
                            .setUrl("http://baobab.kaiyanapp.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83")  //网络请求
                            .setMethod("GET")
                            .setMap(null)
                            .build();
                    HttpUtil.GetHttpString(httpBean, new CallBack() {
                        @Override
                        public void finish(String request) {
                            JsonUtil.getInstance().CategroyJson(request,data,adapter);  //json解析
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                //通知ui更新
                message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }
        }).start();

        return view;
    }
}
