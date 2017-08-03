package com.wxkj.readerfm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wxkj.readerfm.R;
import com.wxkj.readerfm.base.BaseGeneralListFragment;
import com.wxkj.readerfm.base.BaseViewPagerFragment;
import com.wxkj.readerfm.interf.OnTabReselectListener;


/**
 * Created by taosong on 17/6/5.
 */

public class NewsViewPagerFragment extends BaseViewPagerFragment implements OnTabReselectListener {

    public static final String BUNDLE_KEY_REQUEST_CATALOG = "BUNDLE_KEY_REQUEST_CATALOG";
    public static final String CATALOG_YD = "1";
    public static final String CATALOG_QG = "2";
    public static final String CATALOG_LB = "3";
    public static final String CATALOG_XY = "4";
    public static final String CATALOG_YL = "5";
    public static final String CATALOG_LBS = "6";


    /**
     * @param requestCategory 请求类型

     * @return Bundle
     */
    private Bundle getBundle(String requestCategory) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_REQUEST_CATALOG, requestCategory);
        return bundle;
    }


    @Override
    public void onTabReselect() {

        if (mBaseViewPager != null) {
            BaseViewPagerAdapter pagerAdapter = (BaseViewPagerAdapter) mBaseViewPager.getAdapter();
            Fragment fragment = pagerAdapter.getCurFragment();
            if (fragment != null && fragment instanceof BaseGeneralListFragment) {
                ((BaseGeneralListFragment) fragment).onTabReselect();
            }
        }
    }



    @Override
    protected PagerInfo[] getPagers() {

        String[] titles = getResources().getStringArray(R.array.news_viewpage_arrays);
        PagerInfo[] infoList = new PagerInfo[titles.length];
        infoList[0] = new PagerInfo(titles[0], NewsFragment.class,
                getBundle(CATALOG_YD));
        infoList[1] = new PagerInfo(titles[1], NewsFragment.class,
                getBundle(CATALOG_QG));
        infoList[2] = new PagerInfo(titles[2], NewsFragment.class,
                getBundle(CATALOG_LB));

        infoList[3] = new PagerInfo(titles[3], NewsFragment.class,
                getBundle(CATALOG_XY));

        infoList[4] = new PagerInfo(titles[4], NewsFragment.class,
                getBundle(CATALOG_YL));

        infoList[5] = new PagerInfo(titles[5], NewsFragment.class,
                getBundle(CATALOG_LBS));
        return infoList;
    }
}
