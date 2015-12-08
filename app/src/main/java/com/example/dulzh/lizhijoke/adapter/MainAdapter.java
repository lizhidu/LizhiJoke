package com.example.dulzh.lizhijoke.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.R;
import com.example.dulzh.lizhijoke.bean.JokeInfoBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

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
            viewHolder = new ViewHolder(convertView);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置内容
        viewHolder.tvContent.setText(Html.fromHtml(beanList.get(position).getText(), null, null)); //textview解析含有html标签文本
        //标题
        viewHolder.tvTitle.setText(beanList.get(position).getTitle());
        //时间
        if ((beanList.get(position).getCt().substring(0, 10)).equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
            viewHolder.tvTime.setText("最新 " + beanList.get(position).getCt().substring(11, 16));
        } else {
            viewHolder.tvTime.setText(beanList.get(position).getCt().substring(0, 16));
        }

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'layout_listview_content.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */

    class ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.iv_share)
        ImageView ivShare;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
