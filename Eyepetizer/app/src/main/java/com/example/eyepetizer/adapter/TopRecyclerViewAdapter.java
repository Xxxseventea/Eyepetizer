package com.example.eyepetizer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.eyepetizer.R;
import com.example.eyepetizer.bean.TopBean;
import com.example.eyepetizer.interfaces.OnItemClickListener;
import com.example.eyepetizer.util.TimeUtil;

import java.util.ArrayList;

public class TopRecyclerViewAdapter extends RecyclerView.Adapter<TopRecyclerViewAdapter.ViewHolder>{
    @NonNull

    Context context;
    ArrayList<TopBean> datas;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TopRecyclerViewAdapter(Context context, ArrayList<TopBean> datas){
        this.context = context;
        this.datas = datas;
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.title.setText(datas.get(i).getTitles());
        Glide.with(context).load(datas.get(i).getImage()).into(viewHolder.imageView);
        viewHolder.category.setText("#"+datas.get(i).getCategory());
        viewHolder.time.setText(TimeUtil.time(datas.get(i).getTime()));
        if(onItemClickListener != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    String url = datas.get(pos).getUrl();
                    String head = datas.get(pos).getHead();
                    String name = datas.get(pos).getName();
                    String longtitle = datas.get(pos).getDescription();
                    String title = datas.get(pos).getTitles();
                    onItemClickListener.onItemClick(viewHolder.itemView,pos,url,head,name,longtitle,title);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView category;
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.top_item_image);
            title = itemView.findViewById(R.id.top_item_title);
            category = itemView.findViewById(R.id.category);
            time = itemView.findViewById(R.id.time);
        }
    }
}
