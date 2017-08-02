package com.wxkj.readerfm.api.remote;

import android.util.Log;

import com.alibaba.fastjson.JSON;
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
     * @param requestListener
     */
    public static  void requestNewsList(int pageNo,int pageSize,HttpCallback requestListener){

        HttpParams   httpParams = new HttpParams();

        httpParams.put("pageNo",pageNo);

        httpParams.put("pageSize",pageSize);
        String requestUrl = getBaseurl(UrlAddr.API_NEWS_LIST);
        Log.i("TAG","===requestUrl======"+requestUrl+"======httpParams========="+ JSON.toJSONString(httpParams));
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
        Log.i("TAG","===requestUrl======"+requestUrl+"======httpParams========="+ JSON.toJSONString(httpParams));
        AsyncHttpHelp.post(requestUrl,httpParams,requestListener);
    }

}
