package com.wxkj.readerfm.ui;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.kymjs.rxvolley.client.HttpCallback;
import com.wxkj.readerfm.AppContext;
import com.wxkj.readerfm.R;
import com.wxkj.readerfm.api.remote.FmApi;
import com.wxkj.readerfm.base.BaseBackActivity;
import com.wxkj.readerfm.beans.BaseResposeInfo;
import com.wxkj.readerfm.utils.AccountValidatorUtil;
import com.wxkj.readerfm.utils.TDevice;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by taosong on 17/4/16.
 */

public class RegisterActivity  extends BaseBackActivity implements View.OnClickListener {


    @Bind(R.id.et_username)
    AppCompatEditText mEtUserName;

    @Bind(R.id.et_password)
    AppCompatEditText mEtPassword;

    @Bind(R.id.et_repassword)
    AppCompatEditText  mEtRePassword;


    private String mUserName = "";
    private String mPassword = "";

    private String mRePassword = "";


    @Override
    protected int getContentView() {
        return R.layout.app_register;
    }


    @OnClick({R.id.btn_register})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_register:

                handleRegister();

                break;
        }
    }


    private void handleRegister() {

        if (prepareForRegister()) {
            return;
        }
        mUserName = mEtUserName.getText().toString();
        mPassword = mEtPassword.getText().toString();


        FmApi.register(mUserName, mPassword, mHandler);
    }


    private final HttpCallback mHandler = new HttpCallback() {

        @Override
        public void onPreStart() {
            super.onPreStart();
            showWaitDialog(R.string.data_loading);
        }

        @Override
        public void onSuccess(Map<String, String> headers, byte[] t) {
            super.onSuccess(headers, t);
        }

        @Override
        public void onFailure(int errorNo, String strMsg) {
            super.onFailure(errorNo, strMsg);
            requestFailureHint(errorNo);
        }


        @Override
        public void onFinish() {
            super.onFinish();
            hideWaitDialog();
        }

    };


    private boolean prepareForRegister() {
        if (!TDevice.hasInternet()) {
            AppContext.showToastShort(R.string.tip_network_error);
            return true;
        }
        if (mEtUserName.length() == 0 ) {
            mEtUserName.setError("请输入手机号");
            mEtUserName.requestFocus();
            return true;
        }

        if (!AccountValidatorUtil.isMobile(mEtUserName.getText().toString())){
            mEtUserName.setError("请输入有效手机号");
            mEtUserName.requestFocus();
            return true;

        }


        if (mEtPassword.length() == 0) {
            mEtPassword.setError("请输入密码");
            mEtPassword.requestFocus();
            return true;
        }

        if (mEtRePassword.length() == 0){
            mEtRePassword.setError("确认一下密码");
            mEtRePassword.requestFocus();
            return true;

        }

        if (!(mPassword.equals(mRePassword))){

            mEtRePassword.setError("两次输入的密码不一样");
            mEtRePassword.requestFocus();
            return true;
        }

        return false;
    }


    public  void handlerRegisterBean(BaseResposeInfo baseResposeEntity){




    }



    private void handleLoginSuccess(String username,String userpass) {

        Intent data = new Intent();
        data.putExtra("username",username);
        data.putExtra("userpass",userpass);
        setResult(RESULT_OK, data);
        TDevice.hideSoftKeyboard(getWindow().getDecorView());
        finish();
    }


}
