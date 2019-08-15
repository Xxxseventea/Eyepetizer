package com.example.eyepetizer.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.KeySearchAdapter;
import com.example.eyepetizer.bean.HttpBean;
import com.example.eyepetizer.bean.KeySearchBean;
import com.example.eyepetizer.db.SearchListDbOperation;
import com.example.eyepetizer.interfaces.CallBack;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.util.HttpUtil;
import com.example.eyepetizer.util.JsonUtil;

import java.util.ArrayList;

import static com.example.eyepetizer.db.DatabaseHelper.NAME;

public class KeySearch extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<KeySearchBean> data;
    KeySearchAdapter adapter;
    String query;
    String nextpage;
    SearchListDbOperation searchListDbOperation;
    int total;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter = new KeySearchAdapter(KeySearch.this,data);
                    recyclerView.setLayoutManager(new LinearLayoutManager(KeySearch.this));
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int pos, String url, String head, String name, String description,String title) {
                            Intent intent = new Intent(KeySearch.this, VedioDisplay.class);
                            intent.putExtra("url",url);
                            intent.putExtra("head",head);
                            intent.putExtra("name",name);
                            intent.putExtra("description",description);
                            startActivity(intent);
                            searchListDbOperation = new SearchListDbOperation(KeySearch.this,NAME);
                            searchListDbOperation.add(head,name,title);
                            searchListDbOperation.check();
                        }
                    });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_search);
        recyclerView = findViewById(R.id.ksrecyclerview);
        data = new ArrayList<>();
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpBean httpBean = new HttpBean.Builder()
                            .setMap(null)
                            .setMethod("GET")
                            .setUrl("http://baobab.kaiyanapp.com/api/v1/search?num=10&query=" + query)
                            .build();
                    HttpUtil.GetHttpString(httpBean, new CallBack() {
                        @Override
                        public void finish(String request) {
                            JsonUtil.getInstance().SearchJson(request,data,adapter);
                        }
                    });
                    total = data.get(0).getTotal();
                    nextpage = data.get(0).getNextpage();
                  for(int i = 0;i<total/10;i++){
                      HttpBean httpBean1 = new HttpBean.Builder()
                              .setMap(null)
                              .setMethod("GET")
                              .setUrl(nextpage).
                                      build();
                      HttpUtil.GetHttpString(httpBean1, new CallBack() {
                          @Override
                          public void finish(String request) {
                              JsonUtil.getInstance().SearchJson(request,data,adapter);
                          }
                      });
                      nextpage = data.get((i+1)*10).getNextpage();
                  }

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
