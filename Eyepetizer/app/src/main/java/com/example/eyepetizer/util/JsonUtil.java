package com.example.eyepetizer.util;

import com.example.eyepetizer.adapter.CategoryRecyclerViewAdapter;
import com.example.eyepetizer.adapter.HotRecycleViewAdapter;
import com.example.eyepetizer.adapter.KeySearchAdapter;
import com.example.eyepetizer.adapter.TopRecyclerViewAdapter;
import com.example.eyepetizer.bean.CategoryBean;
import com.example.eyepetizer.bean.HotBean;
import com.example.eyepetizer.bean.KeySearchBean;
import com.example.eyepetizer.bean.TopBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtil {          //单例模式
    private static JsonUtil jsonUtil;
    private JsonUtil(){}
    public static synchronized JsonUtil getInstance(){
        if(jsonUtil == null) {
            jsonUtil = new JsonUtil();
        }
        return jsonUtil;
    }
    public void CategroyJson(String request, ArrayList<CategoryBean> data, CategoryRecyclerViewAdapter adapter){    //分类部分的json解析
        try {
            JSONArray jsonArray = new JSONArray(request);
            for (int i = 0; i < 17; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setRight(jsonObject.getString("bgPicture"));
                categoryBean.setRight_text(jsonObject.getString("name"));
                i++;
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                categoryBean.setLeft(jsonObject1.getString("bgPicture"));
                categoryBean.setLeft_text(jsonObject1.getString("name"));
                data.add(categoryBean);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void HotJson(String request, ArrayList<HotBean> hotBeans, HotRecycleViewAdapter adapter){
        try {
            JSONObject jsonObject = new JSONObject(request);
            JSONArray jsonArray = jsonObject.getJSONArray("itemList");

            for (int i = 2; i < 17; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject datas = jsonObject1.getJSONObject("data");
                HotBean hotBean = new HotBean();
                hotBean.setTitle(datas.getString("title"));
                hotBean.setDescription(datas.getString("description"));
                hotBean.setUrl(datas.getString("playUrl"));
                JSONObject author = datas.getJSONObject("author");
                hotBean.setName(author.getString("name"));
                hotBean.setHead(author.getString("icon"));
                JSONObject images = datas.getJSONObject("cover");
                hotBean.setTitleImage(images.getString("feed"));
                hotBeans.add(hotBean);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void SearchJson(String request, ArrayList<KeySearchBean> data, KeySearchAdapter adapter){
        String nextPage;
        int total;
        try {
                JSONObject jsonObject = new JSONObject(request);
                JSONArray jsonArray = jsonObject.getJSONArray("itemList");
                nextPage = jsonObject.getString("nextPageUrl");
                total = jsonObject.getInt("total");
                for(int i = 0;i<10;i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    JSONObject datas = jsonObject1.getJSONObject("data");
                    KeySearchBean keySearchBean = new KeySearchBean();
                    keySearchBean.setNextpage(nextPage);
                    keySearchBean.setTotal(total);
                    keySearchBean.setTitle(datas.getString("title"));
                    keySearchBean.setDescription(datas.getString("description"));
                    keySearchBean.setUrl(datas.getString("playUrl"));
                    JSONObject author = datas.getJSONObject("author");
                    keySearchBean.setName(author.getString("name"));
                    keySearchBean.setHead(author.getString("icon"));
                    JSONObject images = datas.getJSONObject("cover");
                    keySearchBean.setTitleImage(images.getString("feed"));
                    data.add(keySearchBean);
                }
                adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void TopJson(String request, ArrayList<TopBean> data, TopRecyclerViewAdapter adapter){
        try{
            JSONObject jsonObject = new JSONObject(request);
            JSONArray jsonArray = jsonObject.getJSONArray("itemList");
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                JSONObject jsonObject4 = jsonObject2.getJSONObject("author");
                TopBean topBean = new TopBean();
                topBean.setTitles(jsonObject2.getString("title"));
                topBean.setCategory(jsonObject2.getString("category"));
                topBean.setTime(jsonObject2.getInt("duration"));
                JSONObject jsonObject3 = jsonObject2.getJSONObject("cover");
                topBean.setImage(jsonObject3.getString("feed"));
                topBean.setHead(jsonObject4.getString("icon"));
                topBean.setName(jsonObject4.getString("name"));
                topBean.setUrl(jsonObject2.getString("playUrl"));
                topBean.setDescription(jsonObject2.getString("description"));
                data.add(topBean);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
