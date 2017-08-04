package com.wxkj.readerfm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.kymjs.rxvolley.client.HttpCallback;
import com.wxkj.readerfm.api.remote.FmApi;
import com.wxkj.readerfm.app.AppOperator;
import com.wxkj.readerfm.beans.CatalogInfo;
import com.wxkj.readerfm.ui.GuideActivity;
import com.wxkj.readerfm.ui.MainActivity;

import java.util.Map;


/**
 * 应用启动界面
 */
public class WelcomePage extends Activity {


    private Handler dataHandler = new Handler();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 防止第三方跳转时出现双实例
        Activity aty = AppManager.getActivity(MainActivity.class);
        if (aty != null && !aty.isFinishing()) {
            finish();
        }

        setContentView(R.layout.app_start);
        requestNewsCatalog();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 跳转到...
     */
    private void redirectTo() {

        if (AppContext.isFirstStart()) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void requestNewsCatalog() {

        FmApi.requestNewsCatalog(catalogHandler);


    }


    private HttpCallback catalogHandler = new HttpCallback() {

        @Override
        public void onPreStart() {
            super.onPreStart();

        }

        @Override
        public void onSuccess(Map<String, String> headers, byte[] t) {
            super.onSuccess(headers, t);


            String json = new String(t);


            CatalogInfo catalogInfo = AppOperator.createGson().fromJson(json, CatalogInfo.class);
            if (null != catalogInfo && catalogInfo.isSuccess() && catalogInfo.getData().size() > 0) {


                dataHandler.post(new DataHander(0, catalogInfo));

            }

        }

        @Override
        public void onFailure(int errorNo, String strMsg) {
            super.onFailure(errorNo, strMsg);


        }

        @Override
        public void onFinish() {
            super.onFinish();
        }
    };


    private class DataHander implements Runnable {

        private int what;

        private CatalogInfo catalogInfo;

        public DataHander(int what) {
            this.what = what;
        }

        public DataHander(int what,CatalogInfo data) {
            this.what = what;
            this.catalogInfo =data;
        }

        @Override
        public void run() {

            switch (what) {

                case 0:


                    String json  = AppOperator.createGson().toJson(catalogInfo);

                    AppContext.set(AppConfig.CATALOG_JSON_STR,json);

                    findViewById(R.id.app_start_view).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            redirectTo();


                        }
                    }, 800);
                    break;
            }
        }

    }
}
