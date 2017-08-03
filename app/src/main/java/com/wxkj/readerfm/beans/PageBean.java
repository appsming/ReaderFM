package com.wxkj.readerfm.beans;

import java.util.List;

/**
 * Created by taosong on 17/8/3.
 */

public class PageBean<T> extends BaseResposeInfo {
    private List<T> data;
    private String nextPageToken;
    private String prevPageToken;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getPrevPageToken() {
        return prevPageToken;
    }

    public void setPrevPageToken(String prevPageToken) {
        this.prevPageToken = prevPageToken;
    }
}
