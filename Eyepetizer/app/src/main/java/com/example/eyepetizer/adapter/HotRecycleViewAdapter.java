package com.example.eyepetizer.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eyepetizer.bean.HistoryBean;
import com.example.eyepetizer.db.DatabaseHelper;
import com.example.eyepetizer.db.SearchListDbOperation;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.R;
import com.example.eyepetizer.bean.HotBean;
import com.example.eyepetizer.view.activity.MainActivity;

import java.util.ArrayList;

public class HotRecycleViewAdapter extends RecyclerView.Adapter<HotRecycleViewAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<HotBean> data;
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public HotRecycleViewAdapter(Context context, ArrayList<HotBean> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HotRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setTag(i);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final HotRecycleViewAdapter.ViewHolder viewHolder, int i){
         viewHolder.name.setText(data.get(i).getName()+" /");
         viewHolder.title.setText(data.get(i).getTitle());
         Glide.with(context).load(data.get(i).getHead()).into(viewHolder.head);
         Glide.with(context).load(data.get(i).getTitleImage()).into(viewHolder.headImage);
         if(onItemClickListener != null) {
             viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int pos = viewHolder.getLayoutPosition();
                     String url = data.get(pos).getUrl();
                     String head = data.get(pos).getHead();
                     String name = data.get(pos).getName();
                     String title = data.get(pos).getTitle();
                     String longtitle = data.get(pos).getDescription();
                     onItemClickListener.onItemClick(viewHolder.itemView,pos,url,head,name,longtitle,title);
                 }
             });
         }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView title;
        ImageView head;
        ImageView headImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.main_username);
            title = itemView.findViewById(R.id.main_title);
            head = itemView.findViewById(R.id.main_userhead);
            headImage = itemView.findViewById(R.id.main_titleimage);
        }
    }
}
