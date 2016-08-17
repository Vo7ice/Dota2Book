package com.cn.guojinhu.dota2book.ui.news.newslist;

import com.cn.guojinhu.dota2book.bean.News;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public interface OnItemClickListener {

    void onNewsDetail(News news);

    void onPhotoSet(News news);

    void onAdsPhotoSet(String photosetId);
}
