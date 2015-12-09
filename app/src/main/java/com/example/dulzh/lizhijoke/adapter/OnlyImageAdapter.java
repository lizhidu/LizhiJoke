package com.example.dulzh.lizhijoke.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.R;
import com.example.dulzh.lizhijoke.activity.BigImageActivity;
import com.example.dulzh.lizhijoke.bean.JokeImgBean;
import com.example.dulzh.lizhijoke.bean.JokeInfoBean;
import com.example.dulzh.lizhijoke.fragment.OnlyImageFragment;

import org.xutils.common.util.LogUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dulzh on 12/4/15.
 */
public class OnlyImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JokeImgBean.ShowapiResBodyEntity.ContentlistEntity> beanList;

    public OnlyImageAdapter(Context context, ArrayList<JokeImgBean.ShowapiResBodyEntity.ContentlistEntity> beanList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = null;
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listview_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        // 启用图片缩放功能
//        viewHolder.ivContentImg.enable();
//// 禁用图片缩放功能 (默认为禁用，会跟普通的ImageView一样，缩放功能需手动调用enable()启用)
//        viewHolder.ivContentImg.disenable();
//// 获取图片信息
//        Info info = viewHolder.ivContentImg.getInfo();
//// 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
//        viewHolder.ivContentImg.animaFrom(info);
//// 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
//        viewHolder.ivContentImg.animaTo(info, new Runnable() {
//            @Override
//            public void run() {
//                //动画完成监听
//            }
//        });
//// 获取动画持续时间
//        int d = PhotoView.getDefaultAnimaDuring();
        viewHolder.ivContentImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BigImageActivity.class);
                intent.putExtra("url", beanList.get(position).getImg());
                context.startActivity(intent);
            }
        });


        ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                        // 默认自动适应大小
//                .setSize(600, 300)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER).build();

        x.image().bind(viewHolder.ivContentImg, beanList.get(position).getImg(), imageOptions);


        // assets file
        //x.image().bind(iv_big_img, "assets://test.gif", imageOptions);
        // local file
        //x.image().bind(iv_big_img, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
        //x.image().bind(iv_big_img, "/sdcard/test.gif", imageOptions);
        //x.image().bind(iv_big_img, "file:///sdcard/test.gif", imageOptions);
        //x.image().bind(iv_big_img, "file:/sdcard/test.gif", imageOptions);


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
        @Bind(R.id.iv_contentImg)
        ImageView ivContentImg;
        @Bind(R.id.iv_share)
        ImageView ivShare;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
