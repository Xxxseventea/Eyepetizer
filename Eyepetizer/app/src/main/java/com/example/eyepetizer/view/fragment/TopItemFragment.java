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

import com.example.eyepetizer.db.SearchListDbOperation;
import com.example.eyepetizer.view.activity.VedioDisplay;
import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.TopRecyclerViewAdapter;
import com.example.eyepetizer.bean.HttpBean;
import com.example.eyepetizer.bean.TopBean;
import com.example.eyepetizer.interfaces.CallBack;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.util.HttpUtil;
import com.example.eyepetizer.util.JsonUtil;

import java.util.ArrayList;
import java.util.jar.Attributes;

import static com.example.eyepetizer.db.DatabaseHelper.NAME;

public class TopItemFragment extends Fragment {
    TopRecyclerViewAdapter topRecyclerViewAdapter;
    ArrayList<TopBean> datas;
    RecyclerView recyclerView;
    HttpBean httpBean;
    String string;
    SearchListDbOperation searchListDbOperation;
   // TextView textView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    topRecyclerViewAdapter = new TopRecyclerViewAdapter(getContext(),datas);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(topRecyclerViewAdapter);
                    topRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int pos, String url, String head, String name, String description,String title) {
                            Intent intent = new Intent(getActivity(), VedioDisplay.class);
                            intent.putExtra("url",url);
                            intent.putExtra("head",head);
                            intent.putExtra("name",name);
                            intent.putExtra("description",description);
                            startActivity(intent);

                            searchListDbOperation = new SearchListDbOperation(getContext(), "History");
                            searchListDbOperation.add(head,name,title);
                            searchListDbOperation.check();
                        }
                    });
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_item_main,container,false);
       // textView = view.findViewById(R.id.text);
        recyclerView = view.findViewById(R.id.top_rv);
        datas = new ArrayList<>();
        httpConnect(string);
        return view;
    }

    public void httpConnect(final String mod){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpBean = new HttpBean.Builder()
                            .setUrl("http://baobab.kaiyanapp.com/api/v3/ranklist?num=10&strategy=" +mod)
                            .setMethod("GET")
                            .setMap(null)
                            .build();
                    HttpUtil.GetHttpString(httpBean, new CallBack() {
                        @Override
                        public void finish(String request) {
                            JsonUtil.getInstance().TopJson(request, datas, topRecyclerViewAdapter);
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }
        }).start();
    }

}
