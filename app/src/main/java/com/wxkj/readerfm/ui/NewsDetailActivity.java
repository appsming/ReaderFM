package com.wxkj.readerfm.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kymjs.rxvolley.client.HttpCallback;
import com.wxkj.readerfm.R;
import com.wxkj.readerfm.api.remote.FmApi;
import com.wxkj.readerfm.base.BaseBackActivity;
import com.wxkj.readerfm.widget.EmptyLayout;

import java.util.Map;

import butterknife.Bind;

/**
 * Created by taosong on 17/8/3.
 */

public class NewsDetailActivity extends BaseBackActivity {


    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;



    public final static String NEWS_INFO_DETAIL = "NEWS_INFO_DETAIL";
    private  String  newsId = null;

    public static void show(Context context, String  nId) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NEWS_INFO_DETAIL,nId);
        context.startActivity(intent);
    }
    @Override
    protected int getContentView() {
        return R.layout.app_news_detail;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        try {
            newsId = getIntent().getStringExtra(NEWS_INFO_DETAIL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNewsDetail();
            }

        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadNewsDetail();
    }


    public  void loadNewsDetail(){

        if (newsId !=null && newsId.length() >0) {

            FmApi.requestNewsDetail(newsId, newsDetailHandler);
        }
    }



    private HttpCallback newsDetailHandler  = new HttpCallback() {
        @Override
        public void onPreStart() {
            super.onPreStart();
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        }

        @Override
        public void onSuccess(Map<String, String> headers, byte[] responseBody) {
            super.onSuccess(headers, responseBody);
            Log.i("TAG","=newsDetailHandler===onSuccess==responseBody========"+new String(responseBody));
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        }

        @Override
        public void onFailure(int errorNo, String strMsg) {
            super.onFailure(errorNo, strMsg);
            Log.i("TAG","=newsDetailHandler===onFailure==responseBody========"+strMsg);
            requestFailureHint(errorNo);
            mErrorLayout.setErrorType(EmptyLayout.NODATA_ENABLE_CLICK);
        }



        @Override
        public void onFinish() {
            super.onFinish();
        }
    };







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case  R.id.menu_share:

                break;

        }


        return super.onOptionsItemSelected(item);
    }

}
