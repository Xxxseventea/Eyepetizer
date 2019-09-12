package com.example.eyepetizer.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eyepetizer.R;
import com.example.eyepetizer.adapter.ViewPagerAdapter;
import com.example.eyepetizer.view.BaseViewPager;
import com.example.eyepetizer.view.fragment.CategoryFragment;
import com.example.eyepetizer.view.fragment.HotFragment;
import com.example.eyepetizer.view.fragment.TopMainFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView barImage;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private TabLayout tablayout;
    private BaseViewPager viewPager;
    private SearchView searchView;
    private ViewPagerAdapter viewPagerAdapter;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    String[] tabtitles = new String[]{"首页","分类","排行"};
    int[] tabImagesSelect = new int[]{R.drawable.main,R.drawable.hot,R.drawable.top};
    int[] tabimages = new int[]{R.drawable.noselectmain,R.drawable.noselecthot,R.drawable.noselecttop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    public void init(){
        barImage = findViewById(R.id.bar_image);
        toolbar = findViewById(R.id.main_toolbar);
        viewPager = findViewById(R.id.main_viewpager);
        tablayout = findViewById(R.id.main_tablayout);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
//        navigationView.setCheckedItem(R.id.shouye); //默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                  switch (menuItem.getItemId()){
                      case R.id.history:
                          Intent intent = new Intent(MainActivity.this,History.class);
                          startActivity(intent);
                  }
                return true;
            }
        });

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.caidan);
        }
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        fragments.add(new HotFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new TopMainFragment());

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(viewPagerAdapter);
        //tablayout.setupWithViewPager(viewPager);//无自定义view时此方法无效
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        //设置自定义view的适配器
         for(int i = 0;i<3;i++){
             tablayout.addTab(tablayout.newTab().setCustomView(getTab(i)));
         }
         //tab点击事件，让图标变换。
         tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 for(int i = 0;i<3;i++){
                     View view = tablayout.getTabAt(i).getCustomView();
                     ImageView imageView = view.findViewById(R.id.tab_image);
                      TextView textView = view.findViewById(R.id.tab_text);
                      if(i == tab.getPosition()){
                          imageView.setImageResource(tabImagesSelect[i]);
                          textView.setTextColor(getResources().getColor(android.R.color.black));
                      }else{
                          imageView.setImageResource(tabimages[i]);
                          textView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                      }
                 }
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
         });

    }      //初始化控件
    public View getTab(int position){
        View view =  LayoutInflater.from(this).inflate(R.layout.tab_icon,null);
        ImageView imageView = view.findViewById(R.id.tab_image);
        TextView textView = view.findViewById(R.id.tab_text);
        imageView.setImageResource(tabimages[position]);
        textView.setText(tabtitles[position]);
       return view;
    }     //自定义view设计tab

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meau,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, KeySearch.class);
                intent.putExtra("query",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        setMenuListener();
        return true;
    }  //创建menu

    private void setMenuListener() {
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this,"123",Toast.LENGTH_SHORT);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"1234560",Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    //搜索键的点击事件
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return true;
    }
}