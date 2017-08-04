package com.wxkj.readerfm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.wxkj.readerfm.ui.LoginActivity;
import com.wxkj.readerfm.ui.MainActivity;
import com.wxkj.readerfm.ui.RegisterActivity;

/**
 * Created by taosong on 17/8/4.
 */

public class UIHelper {


    /**（1）
     * 显示登录界面
     *
     * @param context
     */
    public static void showLoginActivity(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**（2）
     * 显示注册
     *
     * @param context
     */
    public static void showRegisterActivity(Activity context,int requestCode) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivityForResult(intent,requestCode);
    }



    /**
     * 发送App异常崩溃报告
     *
     * @param context
     */
    public static void sendAppCrashReport(final Context context) {

        DialogHelper.getConfirmDialog(context, "程序发生异常", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 退出
                System.exit(-1);
            }
        }).show();
    }



    public static  void showMainActivity(Context context){

        Intent  intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);
    }

}
