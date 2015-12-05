package com.example.dulzh.lizhijoke;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    int curNum = 0;
    private Handler mHandler = new Handler();
//    private ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    private ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> beanList = new ArrayList<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity>();
    private MainAdapter myAdapter;

    private LoadMoreListView loadMoreListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 获取loadmorelistview，实现加载更多监听事件
        loadMoreListView = (LoadMoreListView) findViewById(R.id.loadMoreListView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        loadMoreListView.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //模拟数据
//        for (int i = 0; i < 20; i++) {
//            Map<String, Object> listItem = new HashMap<>();
//            listItem.put("img", R.mipmap.ic_launcher);
//            listItem.put("text", "Item " + i);
//            mData.add(listItem);
//        }
        requestData();

        //添加listview的adapter
        myAdapter = new MainAdapter(this, beanList);
        loadMoreListView.setAdapter(myAdapter);

    }

    private void requestData() {
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
                java.lang.reflect.Type type = new TypeToken<JokeInfoBean>() {
                }.getType();
                JokeInfoBean jokeInfoBean = gson.fromJson(result, type);
                List<JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity> list = jokeInfoBean.getShowapi_res_body().getContentlist();
                for (JokeInfoBean.ShowapiResBodyEntity.ContentlistEntity bean : list) {
                    beanList.add(bean);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(MainActivity.this, isOnCallback + "", Toast.LENGTH_LONG).show();
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

    /**
     * 模拟下拉刷新时获取新数据
     * simulate getting new data when pull to refresh
     */
//    private void getNewTopData() {
//        Map<String, Object> listItem = new HashMap<>();
//        listItem.put("img", R.mipmap.ic_launcher);
//        listItem.put("text", "New Top Item " + mData.size());
//        mData.add(0, listItem);
//    }

    /**
     * 模拟上拉加载更多时获得更多数据
     * simulate load more data to bottom
     */
//    private void getNewBottomData() {
//        int size = mData.size();
//        for (int i = 0; i < 10; i++) {
//            Map<String, Object> listItem = new HashMap<>();
//            listItem.put("img", R.mipmap.ic_launcher);
//            listItem.put("text", "New Bottom Item " + (size + i));
//            mData.add(listItem);
//
//
//        }
//    }


    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true); //请求开始的时候
//                getNewTopData();
                myAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false); // xx 请求结束的时候
            }
        }, 1000);
    }

    @Override
    public void onLoadMore(AbsListView view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                curNum++;
                if (curNum <= 2) {
//                    getNewBottomData();
                    myAdapter.notifyDataSetChanged();
                    loadMoreListView.onLoadMoreComplete();  //false
                    loadMoreListView.setNoMoreToLoad(false); //有可加载数据
                } else {
                    loadMoreListView.setNoMoreToLoad(true); //数据全部加载完成

                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
