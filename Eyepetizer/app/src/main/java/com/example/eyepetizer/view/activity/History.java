package com.example.eyepetizer.view.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.HistoryAdapter;
import com.example.eyepetizer.bean.HistoryBean;
import com.example.eyepetizer.db.DatabaseHelper;
import com.example.eyepetizer.db.SearchListDbOperation;

import java.util.ArrayList;

import static com.example.eyepetizer.db.DatabaseHelper.NAME;

public class History extends AppCompatActivity {
    private RecyclerView recyclerView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.listview);

        final ArrayList<HistoryBean> historyBeans = new ArrayList<>();
        final SearchListDbOperation searchListDbOperation = new SearchListDbOperation(this,NAME);
        searchListDbOperation.addList(historyBeans);
        HistoryAdapter adapter = new HistoryAdapter(this,historyBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchListDbOperation.deleteData(historyBeans,NAME);
            }
        });
    }
}
