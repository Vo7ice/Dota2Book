package com.cn.guojinhu.dota2book.ui.news;

import retrofit.http.GET;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public interface INewsService {

    @GET("nc/article/headline/{channelId}/{start}-{end}.html")
    void getNewsList(String channelId,int start, int end);

}
