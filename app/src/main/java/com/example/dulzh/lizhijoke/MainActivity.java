package com.example.dulzh.lizhijoke;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.example.dulzh.lizhijoke.adapter.MainAdapter;
import com.example.dulzh.lizhijoke.bean.JokeInfoBean;
import com.example.dulzh.lizhijoke.fragment.HomeFragment;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoadMoreListView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.container)
    ViewPager container;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(container);


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
//        View headerLayout = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
//        navigationView.addHeaderView(headerLayout);
//        CircleImageView circleImageView = (CircleImageView) headerLayout.findViewById(R.id.circleImageView);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) circleImageView.getLayoutParams();
//        layoutParams.width = 250;
//        layoutParams.height = 250;
//        layoutParams.topMargin = 80;
//        circleImageView.setLayoutParams(layoutParams);
//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtil.d("头像点击。。。");
//            }
//        });
        View header = navigationView.getHeaderView(0);
        CircleImageView circleImageView = (CircleImageView) header.findViewById(R.id.circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("头像");
            }
        });





        //模拟数据
//        for (int i = 0; i < 20; i++) {
//            Map<String, Object> listItem = new HashMap<>();
//            listItem.put("img", R.mipmap.ic_launcher);
//            listItem.put("text", "Item " + i);
//            mData.add(listItem);
//        }

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


    }

    @Override
    public void onLoadMore(AbsListView view) {

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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new HomeFragment();
                }
                case 1: {
                    return new HomeFragment();
                }
                case 2: {
                    return new HomeFragment();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "纯图";
                case 1:
                    return "纯文";
                case 2:
                    return "视频";
            }
            return null;
        }
    }
}


