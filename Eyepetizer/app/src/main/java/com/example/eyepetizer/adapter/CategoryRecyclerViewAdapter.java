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
import com.example.eyepetizer.bean.CategoryBean;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {
    Context context;
    ArrayList<CategoryBean> data;

    public CategoryRecyclerViewAdapter(Context context,ArrayList<CategoryBean> data){
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(data.get(i).getLeft()).into(viewHolder.left);
        Glide.with(context).load(data.get(i).getRight()).into(viewHolder.right);
        viewHolder.right_text.setText("#"+data.get(i).getRight_text());
        viewHolder.left_text.setText("#"+data.get(i).getLeft_text());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView left;
        ImageView right;
        TextView left_text;
        TextView right_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            left = itemView.findViewById(R.id.category_left);
            right = itemView.findViewById(R.id.category_right);
            left_text = itemView.findViewById(R.id.left_text);
            right_text = itemView.findViewById(R.id.right_text);
        }
    }
}
