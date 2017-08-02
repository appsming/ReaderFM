package com.wxkj.readerfm;

import android.os.Build;
import android.os.StrictMode;

import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.squareup.okhttp.OkHttpClient;
import com.wxkj.readerfm.base.BaseApplication;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.wxkj.readerfm.AppConfig.KEY_FRITST_START;

/**
 * Created by taosong on 17/8/2.
 */

public class AppContext extends BaseApplication {


    private static AppContext instance;

    //设置连接超时
    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;


    @Override
    public void onCreate() {

        if (AppConfig.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }
        super.onCreate();
        instance = this;

        initHttp();

    }


    private void initHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.setReadTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//设置读取超时时间
        okHttpClient.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);//设置写的超时时间
        okHttpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);//设置连接超时时间

        File cacheFolder = getCacheDir();
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(cacheFolder, new
                OkHttpStack(okHttpClient)));
    }


    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }


    public static boolean isFirstStart() {
        return getPreferences().getBoolean(KEY_FRITST_START, true);
    }

    public static void setFirstStart(boolean frist) {
        set(KEY_FRITST_START, frist);
    }


}
