package com.example.eyepetizer.view.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.ViewPagerAdapter;
import com.example.eyepetizer.view.fragment.TopHistorical;

import java.util.ArrayList;

public class TopMainFragment extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("tag","topfragmentcreate");
            view = inflater.inflate(R.layout.activity_top_mainfragment, container, false);
            tabLayout = view.findViewById(R.id.classify_tab);
            viewPager = view.findViewById(R.id.classify_viewpager);
            titles = new ArrayList<>();
            fragments = new ArrayList<>();
            titles.add("周排行");
            titles.add("月排行");
            titles.add("总排行");
            fragments.add(new TopWeekly());
            fragments.add(new TopMonthly());
            fragments.add(new TopHistorical());
            viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragments, titles);
            viewPager.setAdapter(viewPagerAdapter);
          //  viewPager.setOffscreenPageLimit(3);
            tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
