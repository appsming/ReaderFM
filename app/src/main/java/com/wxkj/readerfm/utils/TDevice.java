package com.wxkj.readerfm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wxkj.readerfm.AppContext;
import com.wxkj.readerfm.base.BaseApplication;

import org.kymjs.kjframe.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taosong on 17/8/2.
 */

public class TDevice {

    // 手机网络类型
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;


    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }

    public static void installAPK(Context context, File file) {
        if (file == null || !file.exists())
            return;
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }



    public static float dp2px(float dp) {
        return dp * getDisplayMetrics().density;
    }

    public static float px2dp(float px) {
        return px / getDisplayMetrics().density;
    }

    public static DisplayMetrics getDisplayMetrics() {
        return BaseApplication.context().getResources().getDisplayMetrics();
    }


    public static float getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) BaseApplication.context()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }




    public static boolean  isAvilible(Context context,String  markerPkName){

        final PackageManager packageManager = context.getPackageManager();

        List<PackageInfo>  packageInfos = packageManager.getInstalledPackages(0);

        List<String> nameList = new ArrayList<>();

        if (packageInfos !=null){

            for (int i = 0 ; i < packageInfos.size(); i++){

                String pn = packageInfos.get(i).packageName;

                nameList.add(pn);
            }



        }


        return  nameList.contains(markerPkName);
    }


    public static void hideSoftKeyboard(View view) {
        if (view == null) return;
        View mFocusView = view;

        Context context = view.getContext();
        if (context != null && context instanceof Activity) {
            Activity activity = ((Activity) context);
            mFocusView = activity.getCurrentFocus();
        }
        if (mFocusView == null) return;
        mFocusView.clearFocus();
        InputMethodManager manager = (InputMethodManager) mFocusView.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mFocusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showSoftKeyboard(View view) {
        if (view == null) return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        if (!view.isFocused()) view.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }


    public static int getVersionCode() {
        return getVersionCode(BaseApplication.context().getPackageName());
    }

    public static int getVersionCode(String packageName) {
        try {
            return BaseApplication.context()
                    .getPackageManager()
                    .getPackageInfo(packageName, 0)
                    .versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            return 0;
        }
    }

    public static String getVersionName() {
        try {
            return BaseApplication
                    .context()
                    .getPackageManager()
                    .getPackageInfo(BaseApplication.context().getPackageName(), 0)
                    .versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            return "undefined version name";
        }
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) AppContext
                .getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }


    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);

        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);

        // Reset the drawing cache background color to fully transparent
        // for the duration of this operation
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            Log.e("Folder", "failed getViewBitmap(" + v + ")", new RuntimeException());
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        // Restore the view
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

}
