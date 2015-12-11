package com.example.dulzh.lizhijoke.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.BaseFragment;
import com.example.dulzh.lizhijoke.MyApplication;
import com.example.dulzh.lizhijoke.R;
import com.example.dulzh.lizhijoke.adapter.OnlyImageAdapter;
import com.example.dulzh.lizhijoke.bean.JokeImgBean;
import com.example.dulzh.lizhijoke.loadingview.LoadingView;
import com.example.dulzh.lizhijoke.utils.Common;
import com.example.dulzh.lizhijoke.widget.LoadMoreListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nineoldandroids.animation.ObjectAnimator;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * create an instance of this fragment.
 */
public class OnlyImageFragment extends BaseFragment implements LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.loadView)
    LoadingView loadView;
    @Bind(R.id.mToolbar)
    Toolbar mToolbar;

    ObjectAnimator mAnimator;

    private Handler mHandler = new Handler();
    //    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private ArrayList<JokeImgBean.ShowapiResBodyEntity.ContentlistEntity> beanList = new ArrayList<>();
    private OnlyImageAdapter myAdapter;
    private static int page = 1;
    private LoadMoreListView loadMoreListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static int mTouchSlop;
    private TabLayout tabs;
    private float mFirstY, mCurrentY;//记录当前和原先的坐标
    private int direction; //记录滑动方向
    private boolean mShow = true; //隐藏和显示表示
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image, container, false);
        // 获取loadmorelistview，实现加载更多监听事件
        loadMoreListView = (LoadMoreListView) view.findViewById(R.id.loadMoreListView);
        TextView textView = (TextView) view.findViewById(R.id.tv_no_data);
        loadMoreListView.setEmptyView(textView);
        View header = new View(getActivity());
        header.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        loadMoreListView.addHeaderView(header);
        mTouchSlop = ViewConfiguration.get(this.getActivity()).getScaledTouchSlop(); //获取最低滑动距离

        loadMoreListView.setOnLoadMoreListener(this);
        tabs = (TabLayout) getActivity().findViewById(R.id.tabs);
        viewPager = (ViewPager) getActivity().findViewById(R.id.container);
        //listview的事件分发机制
        loadMoreListView.setOnTouchListener(myTouchListener);

        requestData(1);
        //添加listview的adapter
        myAdapter = new OnlyImageAdapter(this.getActivity(), beanList);
        loadMoreListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        ButterKnife.bind(this, view);
        return view;
    }

    View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchSlop) {
                        direction = 0;// down

                        LogUtil.d(mCurrentY+"-"+mFirstY+"---"+mTouchSlop);
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        direction = 1;// up
                        LogUtil.d(mCurrentY+"-"+mFirstY+"---"+mTouchSlop);

                    }
                    if (direction == 1) {
                        if (mShow) {
                            toolbarAnim(1);//show
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolbarAnim(0);//hide
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };


    private void toolbarAnim(int flag) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (flag == 0) {
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(), 0);
        } else {
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY", mToolbar.getTranslationY(),
                    -mToolbar.getHeight());
        }
        mAnimator.start();
    }

    private void requestData(int page) {
        RequestParams params = new RequestParams(Common.URL_JOKE_IMG);
        params.addQueryStringParameter("page", Integer.toString(page));
        params.addHeader("apikey", MyApplication.API_KEY);
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CacheCallback<String>() {

            private boolean hasError = false;
            private String result = null;

            @Override
            public boolean onCache(String result) {

                // 得到缓存数据
                //
                // * 客户端会根据服务端返回的 header 中 max-age 或 expires 来确定本地缓存是否给 onCache 方法.
                //   如果服务端没有返回 max-age 或 expires, 那么缓存将一直保存, 除非这里自己定义了返回false的
                //   逻辑, 那么xUtils将请求新数据, 来覆盖它.
                //
                // * 如果信任该缓存返回 true, 将不再请求网络;
                //   返回 false 继续请求网络, 但会在请求头中加上ETag, Last-Modified等信息,
                //   如果服务端返回304, 则表示数据没有更新, 不继续加载数据.
                //
                this.result = result;
                return false; // true: 信任缓存数据; false不信任缓存数据.
            }


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
                loadView.setVisibility(View.GONE);
                Gson gson = new Gson();
                Type type = new TypeToken<JokeImgBean>() {
                }.getType();
                JokeImgBean jokeInfoBean = gson.fromJson(result, type);
                List<JokeImgBean.ShowapiResBodyEntity.ContentlistEntity> list = jokeInfoBean.getShowapi_res_body().getContentlist();
                //没必要再写一个beanlist存储bean
                for (JokeImgBean.ShowapiResBodyEntity.ContentlistEntity bean : list) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
