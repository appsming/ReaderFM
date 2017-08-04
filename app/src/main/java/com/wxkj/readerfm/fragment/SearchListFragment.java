package com.wxkj.readerfm.fragment;

import android.util.Log;

import com.wxkj.readerfm.base.BaseGeneralListFragment;
import com.wxkj.readerfm.base.BaseListAdapter;
import com.wxkj.readerfm.beans.NewsInfo;

import java.lang.reflect.Type;

/**
 * Created by taosong on 17/8/4.
 */

public class SearchListFragment  extends BaseGeneralListFragment<NewsInfo> {
    @Override
    protected BaseListAdapter<NewsInfo> getListAdapter() {
        return null;
    }

    @Override
    protected Type getType() {
        return null;
    }

    // 调用此方法请求搜索内容
    public  void loadNews(String searchKey){


        Log.i("TAG","=======searchKey========="+searchKey);


    }
}
