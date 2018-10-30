package com.example.caoyuan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.TabLayout;


import com.example.caoyuan.yuekaocaoyaun20181026.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment{
    private TabLayout tab1;
    private List<Fragment> wlist = new ArrayList<>();
    private  String[] wtitle={"热门影片","正在热映","即将上映"};
    private MyaAdapter adapter;
    private ViewPager vp1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
       tab1 =  (TabLayout) view.findViewById(R.id.tab1);
        vp1 =  (ViewPager)view.findViewById(R.id.vp1);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        wlist.add(new Fragment2());
        wlist.add(new Fragment2());
        wlist.add(new Fragment4());


        adapter = new MyaAdapter(getActivity().getSupportFragmentManager());
        vp1.setAdapter(adapter);
        tab1.setupWithViewPager(vp1);


    }

    private class MyaAdapter extends FragmentPagerAdapter {
        public MyaAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return wlist.get(position);
        }

        @Override
        public int getCount() {
            return wlist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return wtitle[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
    }

