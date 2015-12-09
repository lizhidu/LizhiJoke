package com.example.dulzh.lizhijoke.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.dulzh.lizhijoke.BaseActivity;
import com.example.dulzh.lizhijoke.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BigImageActivity extends BaseActivity {


    @Bind(R.id.iv_big_img)
    ImageView ivBigImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);

        ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                // 默认自动适应大小
                // .setSize(...)
                .setIgnoreGif(false)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER).build();

        x.image().bind(ivBigImg, getIntent().getStringExtra("url"), imageOptions);

        // assets file
        //x.image().bind(iv_big_img, "assets://test.gif", imageOptions);

        // local file
        //x.image().bind(iv_big_img, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
        //x.image().bind(iv_big_img, "/sdcard/test.gif", imageOptions);
        //x.image().bind(iv_big_img, "file:///sdcard/test.gif", imageOptions);
        //x.image().bind(iv_big_img, "file:/sdcard/test.gif", imageOptions);
    }
}
