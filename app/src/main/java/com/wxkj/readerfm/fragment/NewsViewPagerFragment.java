package com.wxkj.readerfm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.wxkj.readerfm.AppConfig;
import com.wxkj.readerfm.AppContext;
import com.wxkj.readerfm.app.AppOperator;
import com.wxkj.readerfm.base.BaseGeneralListFragment;
import com.wxkj.readerfm.base.BaseViewPagerFragment;
import com.wxkj.readerfm.beans.CatalogInfo;
import com.wxkj.readerfm.interf.OnTabReselectListener;

import java.util.ArrayList;

public class NewsViewPagerFragment extends BaseViewPagerFragment implements OnTabReselectListener {

    public static final String BUNDLE_KEY_REQUEST_CATALOG = "BUNDLE_KEY_REQUEST_CATALOG";

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


        String json = AppContext.get(AppConfig.CATALOG_JSON_STR, "");

        Log.i("TAG","=====取出 ============"+json);

        PagerInfo[] infoList = null;

        if (json.length() == 0) {


        } else {


            CatalogInfo data =  AppOperator.createGson().fromJson(json, CatalogInfo.class);

            ArrayList<CatalogInfo.CatalogBean> catalogsInfo =  data.getData();


            if (null !=catalogsInfo &&  catalogsInfo.size() >0 ){


                    infoList = new PagerInfo[catalogsInfo.size()];

                    for (int i = 0; i < catalogsInfo.size(); i++) {

                        CatalogInfo.CatalogBean catalogInfo = catalogsInfo.get(i);

                        infoList[i] = new PagerInfo(catalogInfo.getName(), NewsFragment.class,
                                getBundle(catalogInfo.get_id()));

                    }


            }
        }

        return infoList;
    }
}