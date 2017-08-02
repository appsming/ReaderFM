package com.wxkj.readerfm.base;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxkj.readerfm.AppConfig;
import com.wxkj.readerfm.AppContext;
import com.wxkj.readerfm.R;
import com.wxkj.readerfm.utils.DoubleClickExitHelper;

import butterknife.Bind;

/**
 * Created by taosong on 17/8/2.
 */

public  class BaseMainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DoubleClickExitHelper mDoubleClickExit;

    private ImageView mHeaderIcon;

    private TextView mHeaderName;

    private TextView mHeaderTime;


    @Bind(R.id.content)
    protected FrameLayout mContent;

    @Override
    protected int getContentView() {
        return R.layout.app_main;
    }

    @Override
    public void initWidget() {



        mDoubleClickExit = new DoubleClickExitHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View heanderView = navigationView.getHeaderView(0);
        mHeaderIcon = (ImageView) heanderView.findViewById(R.id.header_icon);
        mHeaderName = (TextView) heanderView.findViewById(R.id.header_name);
        mHeaderTime = (TextView) heanderView.findViewById(R.id.header_time);
        heanderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }


    public void setupBackAsUp(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //为标题栏设置标题，即给ActionBar设置标题。
            actionBar.setTitle(title);
            //ActionBar加一个返回图标
            actionBar.setDisplayHomeAsUpEnabled(false);
            //不显示当前程序的图标。
            actionBar.setDisplayShowHomeEnabled(true);
        }
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
    protected void onResume() {
        super.onResume();
        setupBackAsUp(getString(R.string.app_name));
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case 0:
                break;


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){

            // 是否退出应用
            if (AppContext.get(AppConfig.KEY_DOUBLE_CLICK_EXIT, true)) {
                return mDoubleClickExit.onKeyDown(keyCode, event);
            }
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



}

