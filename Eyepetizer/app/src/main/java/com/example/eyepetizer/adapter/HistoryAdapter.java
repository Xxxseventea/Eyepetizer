package com.example.eyepetizer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eyepetizer.R;
import com.example.eyepetizer.bean.HistoryBean;
import com.example.eyepetizer.db.DatabaseHelper;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.view.activity.KeySearch;

import java.util.ArrayList;
import java.util.List;

import static com.example.eyepetizer.db.DatabaseHelper.NAME;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<HistoryBean> historyBeans;
    DatabaseHelper databaseHelper;

    public HistoryAdapter(Context context, ArrayList<HistoryBean> historyBeans){
        this.context = context;
        this.historyBeans = historyBeans;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.historyitem,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
             viewHolder.title.setText(historyBeans.get(i).getTitle());
             viewHolder.name.setText(historyBeans.get(i).getName());
             Glide.with(context).load(historyBeans.get(i).getImage()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return historyBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView title;
        ImageView imageView;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.history_name);
            title = itemView.findViewById(R.id.history_title);
            imageView = itemView.findViewById(R.id.history_image);
        }
    }
}
