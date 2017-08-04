package com.wxkj.readerfm.ui;

import android.app.Activity;
import android.content.Intent;

import com.wxkj.readerfm.R;
import com.wxkj.readerfm.base.BaseBackActivity;
import com.wxkj.readerfm.beans.UserInfo;

/**
 * Created by taosong on 17/8/4.
 */

public class UserInfoActivity  extends BaseBackActivity {
    @Override
    protected int getContentView() {
        return R.layout.app_user_detail;
    }


    public  static  void show(Activity activity, UserInfo  userInfo){

        Intent  intent  = new Intent(activity,UserInfoActivity.class);
        intent.putExtra("userInfo",userInfo);
        activity.startActivity(intent);
    }
}
