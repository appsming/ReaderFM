package com.wxkj.readerfm.base;


import com.wxkj.readerfm.interf.OnTabReselectListener;

/**
 * Created by JuQiu
 * on 16/6/6.
 */

public abstract class BaseGeneralListFragment<T> extends BaseListFragment<T> implements OnTabReselectListener {
    @Override
    public void onTabReselect() {
        if(mListView != null){
            mCurrentPage =1;
            mListView.setSelection(0);
            mRefreshLayout.setRefreshing(true);
            onRefreshing();
        }
    }
}
