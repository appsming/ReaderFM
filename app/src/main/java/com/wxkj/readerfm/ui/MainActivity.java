package com.wxkj.readerfm.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wxkj.readerfm.R;
import com.wxkj.readerfm.base.BaseMainActivity;
import com.wxkj.readerfm.fragment.NewsViewPagerFragment;

public class MainActivity extends BaseMainActivity{

    NewsViewPagerFragment newsViewPagerFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsViewPagerFragment  = new NewsViewPagerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.content,  newsViewPagerFragment).commit();

    }


}

