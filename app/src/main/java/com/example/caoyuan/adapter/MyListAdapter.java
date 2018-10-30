package com.example.caoyuan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caoyuan.bean.Bean;
import com.example.caoyuan.yuekaocaoyaun20181026.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyListAdapter extends BaseAdapter{
    private Context context;
    private List<Bean.ResultBean> list;

    public MyListAdapter(Context context, List<Bean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Holder holder;
      if (convertView == null){
          holder = new Holder();
          convertView = View.inflate(context, R.layout.item, null);
          holder.title1 = convertView.findViewById(R.id.title1);
          holder.title2 = convertView.findViewById(R.id.title2);
          holder.img1 = convertView.findViewById(R.id.img1);
          convertView.setTag(holder);
      }else {
          holder =(Holder) convertView.getTag();

      }
      holder.title1.setText(list.get(position).getName());
        holder.title2.setText(list.get(position).getSummary());
        ImageLoader.getInstance().displayImage(list.get(position).getImageUrl(),holder.img1);

        return convertView;
    }
    class Holder{
        TextView title1,title2;
        ImageView img1;

    }
}
