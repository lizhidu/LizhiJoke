package com.example.dulzh.lizhijoke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.R;
import com.example.dulzh.lizhijoke.bean.JokeInfoBean;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dulzh on 12/4/15.
 */
public class MainAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> beanList;

    public MainAdapter(Context context, ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> beanList) {
        this.context = context;
        this.beanList = beanList;
    }


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listview_content, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_content);
//            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(beanList.get(position).getTitle());
//        viewHolder.imageView.setImageResource((Integer) mData.get(position).get("img"));
        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
