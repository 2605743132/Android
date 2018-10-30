package com.example.caoyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caoyuan.adapter.MyListAdapter;
import com.example.caoyuan.bean.Bean;
import com.example.caoyuan.helper.Helper;
import com.example.caoyuan.yuekaocaoyaun20181026.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {
    private int page =1;
    private PullToRefreshListView lv;

    private List<Bean.ResultBean> list = new ArrayList<>();
    private MyListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        lv =(PullToRefreshListView) view.findViewById(R.id.lv1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         adapter = new MyListAdapter(getActivity(),list);
        lv.setAdapter(adapter);
        inithelper(page);
        lv.setMode(PullToRefreshBase.Mode.BOTH);
         lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
             @Override
             public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                 page =1;
                 inithelper(page);
                 doDate();
             }



             @Override
             public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
               if (page>=2){


                   Toast.makeText(getActivity(),"没有更多数据了",Toast.LENGTH_SHORT).show();
                   doDate();


               }else {
                   page++;
                   inithelper(page);
                   doDate();

               }

             }
         });
    }
    private void doDate() {
lv.postDelayed(new Runnable() {
    @Override
    public void run() {
        lv.onRefreshComplete();
    }
},1000);



    }
    private void inithelper(int page) {
      new Helper().get("http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?userId=18&sessionId=15320748258726&page="+page+"&count=10").Result(new Helper.HttpListener() {
          @Override
          public void success(String data) {
              //调用网络工具类
              Gson gson = new Gson();
              Bean bean = gson.fromJson(data, Bean.class);
              List<Bean.ResultBean> item = bean.getResult();
              list.addAll(item);
              adapter.notifyDataSetChanged();
          }
      });

    }
}
