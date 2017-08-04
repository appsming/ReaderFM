package com.wxkj.readerfm.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.reflect.TypeToken;
import com.wxkj.readerfm.AppConfig;
import com.wxkj.readerfm.adapter.NewsListAdapter;
import com.wxkj.readerfm.api.remote.FmApi;
import com.wxkj.readerfm.base.BaseGeneralListFragment;
import com.wxkj.readerfm.base.BaseListAdapter;
import com.wxkj.readerfm.beans.NewsInfo;
import com.wxkj.readerfm.beans.PageBean;
import com.wxkj.readerfm.ui.NewsDetailActivity;

import java.lang.reflect.Type;

/**
 * Created by taosong on 17/8/2.
 */

public class NewsFragment  extends BaseGeneralListFragment<NewsInfo>  {

    public String requestCategory = "";//请求类型
    public static final String CATEGORY_DEFAULE ="";


    @Override
    protected void initBundle(Bundle bundle) {
        super.initBundle(bundle);
        requestCategory = bundle.getString(NewsViewPagerFragment.BUNDLE_KEY_REQUEST_CATALOG, CATEGORY_DEFAULE);

    }

    /**
     * fragment被销毁的时候重新调用，初始化保存的数据
     *
     * @param bundle onSaveInstanceState
     */
    @Override
    protected void onRestartInstance(Bundle bundle) {
        super.onRestartInstance(bundle);
        requestCategory = bundle.getString(NewsViewPagerFragment.BUNDLE_KEY_REQUEST_CATALOG, CATEGORY_DEFAULE);

    }

    @Override
    protected void initData() {
        super.initData();


    }


    @Override
    protected void requestData() {
        super.requestData();
        loadNews();

    }

    public void loadNews() {


        FmApi.requestNewsList(mCurrentPage, AppConfig.PAGE_SIZE, requestCategory, mHandler);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.show(getContext(), mAdapter.getItem(position).get_id() + "");
    }


    @Override
    protected BaseListAdapter getListAdapter() {
        return new NewsListAdapter(this);
    }

    @Override
    protected Type getType() {
        return new TypeToken<PageBean<NewsInfo>>() {
        }.getType();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(NewsViewPagerFragment.BUNDLE_KEY_REQUEST_CATALOG, requestCategory);
        super.onSaveInstanceState(outState);
    }
}