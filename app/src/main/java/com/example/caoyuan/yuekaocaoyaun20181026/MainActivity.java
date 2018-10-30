package com.example.caoyuan.yuekaocaoyaun20181026;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caoyuan.fragment.Fragment1;
import com.example.caoyuan.fragment.Fragment3;
import com.example.caoyuan.fragment.Fragment5;
import com.example.caoyuan.fragment.Fragment6;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
   private List<Fragment> mlist = new ArrayList<>();
   private  String[] mtitle={"影片","影院","会员","设置"};
    private MyAdapter adapter;
    private ListView bj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化变量
        initView();

       //配置适配器
       initDate();

    }



    private void initView() {
        tab=(TabLayout)findViewById(R.id.tab);
        vp =  (ViewPager) findViewById(R.id.vp);
        bj=(ListView)findViewById(R.id.bj);
    }
    private void initDate() {
        //准备数据源，这里多写点数据，以便于观看滚动条
        String[] data={"我的收藏","我的关注","我的帖子","我的积分","我的通知","设置"};
        //配置适配器
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                data
        );
        bj.setAdapter(arrayAdapter);
        bj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"dddddd",Toast.LENGTH_SHORT).show();
            }
        });


        mlist.add(new Fragment1());
        mlist.add(new Fragment3());
        mlist.add(new Fragment5());
        mlist.add(new Fragment6());


        adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);


    }
    private class MyAdapter extends FragmentPagerAdapter{
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mtitle[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
