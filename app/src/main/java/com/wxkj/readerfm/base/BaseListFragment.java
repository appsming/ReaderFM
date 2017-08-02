package com.wxkj.readerfm.base;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wxkj.readerfm.R;
import com.wxkj.readerfm.widget.SuperRefreshLayout;

import java.util.Date;

/**
 * Created by taosong on 17/8/2.
 */


public abstract class BaseListFragment<T> extends BaseFragment implements
        SuperRefreshLayout.SuperRefreshLayoutListener,
        AdapterView.OnItemClickListener, BaseListAdapter.Callback,
        View.OnClickListener {

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_NO_MORE = 2;
    public static final int TYPE_ERROR = 3;
    public static final int TYPE_NET_ERROR = 4;
    protected String CACHE_NAME = getClass().getName();
    protected ListView mListView;
    protected SuperRefreshLayout mRefreshLayout;
//    protected EmptyLayout mErrorLayout;
    protected BaseListAdapter<T> mAdapter;
    protected boolean mIsRefresh;
  //  protected TextHttpResponseHandler mHandler;
   // protected PageBean<T> mBean;
    private View mFooterView;
    private ProgressBar mFooterProgressBar;
    private TextView mFooterText;
    private String mTime;

    protected int mCurrentPage = 1;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_list;
    }

    @Override
    public Date getSystemTime() {
        return null;
    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onLoadMore() {

    }
}
