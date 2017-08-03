package com.wxkj.readerfm.adapter;

import com.wxkj.readerfm.R;
import com.wxkj.readerfm.base.BaseListAdapter;
import com.wxkj.readerfm.base.ViewHolder;
import com.wxkj.readerfm.beans.NewsInfo;

/**
 * Created by taosong on 17/8/3.
 */

public class NewsListAdapter  extends BaseListAdapter<NewsInfo>{

    public NewsListAdapter(Callback callback) {
        super(callback);
    }


    @Override
    protected void convert(ViewHolder vh, NewsInfo item, int position) {

        vh.setText(R.id.tv_title, item.getTitle());

        vh.setText(R.id.tv_author, item.getTextAuthor());
        vh.setText(R.id.tv_reader, item.getVoiceAuthor());
        vh.setText(R.id.tv_time, item.getVoiceTime());
        vh.setText(R.id.tv_read_times,item.getListenCount());
        vh.setText(R.id.tv_content,item.getDescs());
        vh.setImageForNet(R.id.iv_image, item.getImgUrl());

    }






        @Override
    protected int getLayoutId(int position, NewsInfo item) {
        return R.layout.list_news_item;
    }


}
