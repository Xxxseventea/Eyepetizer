package com.example.eyepetizer.interfaces;

import android.view.View;

import com.example.eyepetizer.bean.HotBean;

import java.util.ArrayList;

public interface OnItemClickListener {
    void onItemClick(View view,int pos,String url,String head,String name,String description,String title);
}
