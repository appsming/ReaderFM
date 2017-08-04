package com.wxkj.readerfm.api.remote;

import android.util.Log;

import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.wxkj.readerfm.api.AsyncHttpHelp;
import com.wxkj.readerfm.api.UrlAddr;

/**
 * Created by taosong on 17/8/2.
 */

public class FmApi {

    private static String HTTPS = "http://";

    public static String HOST = "120.25.148.78";



    public  static String getBaseurl(String apiPath) {
        String requestUrl = HTTPS +HOST+apiPath;

        Log.i("TAG","=======requestUrl========="+requestUrl);

        return requestUrl;

    }


    /**
     * (1) 新闻列表
     * @param pageNo  页码
     * @param pageSize  页面大小 （默认15条或20条）
     * @category 分类ID
     * @param requestListener
     */
    public static  void requestNewsList(int pageNo,int pageSize,String category,HttpCallback requestListener){

        HttpParams   httpParams = new HttpParams();

        httpParams.put("pageNo",pageNo);
        httpParams.put("pageSize",pageSize);
        httpParams.put("category",category);
        String requestUrl = getBaseurl(UrlAddr.API_NEWS_LIST);
        AsyncHttpHelp.post(requestUrl,httpParams,requestListener);
    }


    /**
     * (2) 新闻详情
     * @param newsId 新闻ID
     * @param requestListener
     */
    public static  void requestNewsDetail(String newsId,HttpCallback requestListener){

        HttpParams   httpParams = new HttpParams();

        httpParams.put("_id",newsId);

        String requestUrl = getBaseurl(UrlAddr.API_NEWS_DETAIL);

        AsyncHttpHelp.post(requestUrl,httpParams,requestListener);
    }


    /**
     * 用户登录
     * @param username
     * @param password
     * @param handler
     */
    public static void login(String username,String password, HttpCallback handler){

        HttpParams   httpParams = new HttpParams();

        httpParams.put("uname",username);
        httpParams.put("upass",password);
        String requestUrl = getBaseurl(UrlAddr.API_USER_LOGIN);

        AsyncHttpHelp.post(requestUrl,httpParams,handler);

    }


    /**
     * 用户注册
     * @param username
     * @param password
     * @param handler
     */
    public static  void register(String username,String password, HttpCallback handler){


        HttpParams   httpParams = new HttpParams();

        httpParams.put("uname",username);
        httpParams.put("upass",password);
        String requestUrl = getBaseurl(UrlAddr.API_USER_LOGIN);

        AsyncHttpHelp.post(requestUrl,httpParams,handler);


    }



}
