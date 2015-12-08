package com.example.dulzh.lizhijoke.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.BaseFragment;
import com.example.dulzh.lizhijoke.MyApplication;
import com.example.dulzh.lizhijoke.R;
import com.example.dulzh.lizhijoke.adapter.MainAdapter;
import com.example.dulzh.lizhijoke.bean.JokeInfoBean;
import com.example.dulzh.lizhijoke.utils.Common;
import com.example.dulzh.lizhijoke.widget.LoadMoreListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private Handler mHandler = new Handler();
    //    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> beanList = new ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity>();
    private MainAdapter myAdapter;
    private static int page = 1;
    private LoadMoreListView loadMoreListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // 获取loadmorelistview，实现加载更多监听事件
        loadMoreListView = (LoadMoreListView) view.findViewById(R.id.loadMoreListView);
        TextView textView = (TextView) view.findViewById(R.id.tv_no_data);
        loadMoreListView.setEmptyView(textView);
        loadMoreListView.setOnLoadMoreListener(this);

        requestData(1);
        //添加listview的adapter
        myAdapter = new MainAdapter(this.getActivity(), beanList);
        loadMoreListView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();



        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    private void requestData(int page) {
        RequestParams params = new RequestParams(Common.URL_JOKEINFO);
        params.addQueryStringParameter("page", Integer.toString(page));
        params.addHeader("apikey", MyApplication.API_KEY);
        x.http().get(params, new Callback.CommonCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public void onSuccess(String result) {
                this.result = result;

//                {
//                    "showapi_res_code": 0,
//                        "showapi_res_error": "",
//                        "showapi_res_body": {
//                    "allNum": 13062,
//                            "allPages": 654,
//                            "contentlist": [
//                    {
//                        "ct": "2015-12-04 18:10:18.974",
//                            "text": "晚上看到女神在线，我发了一个信息给她：”在吗？”十分钟后女神回道：”在拉，有事吗？”天哪！女神居然回复我了！我按耐住激动的心情回复：”那你先拉，拉完再聊。”一个小时过去了，女神怎么还没拉完？",
//                            "title": "女神还没拉完吗？",
//                            "type": 1
//                    }
//                    ],
//                    "currentPage": 1,
//                            "maxResult": 20,
//                            "ret_code": 0
//                }
//                }

                Gson gson = new Gson();
                Type type = new TypeToken<JokeInfoBean>() {
                }.getType();
                JokeInfoBean jokeInfoBean = gson.fromJson(result, type);
                List<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> list = jokeInfoBean.getShowapi_res_body().getContentlist();
                //没必要再写一个beanlist存储bean
                for (JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity bean : list) {
                    beanList.add(bean);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                boolean hasError = true;
                if (ex instanceof HttpException) { // 网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    LogUtil.d(responseCode + responseMsg + errorResult);
                } else { // 其他错误
                    LogUtil.d(ex + "");
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {
//                Toast.makeText(MainActivity.this, cex + "", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFinished() {
                if (!hasError && result != null) {
                    // 成功获取数据
//                    Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    @Override
    public void onLoadMore(AbsListView view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                requestData(page);
                loadMoreListView.onLoadMoreComplete();  //false
                loadMoreListView.setNoMoreToLoad(false); //有可加载数据
//                loadMoreListView.setNoMoreToLoad(true); //数据全部加载完成

            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                beanList.clear();
                mSwipeRefreshLayout.setRefreshing(true); //请求开始的时候
                requestData(1);
                mSwipeRefreshLayout.setRefreshing(false); // xx 请求结束的时候
            }
        }, 1000);
    }
}
