package com.cn.guojinhu.dota2book.IService;


import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public interface INewsService {

    @GET("{channelId}/{start}-{end}.html")
    Observable<NewsBean> getNewsList(@Path("channelId") String channelId,
                                          @Path("start") int start,
                                          @Path("end") int end);

    @GET("{channelId}/{start}-{end}.html")
    Call<ResponseBody> getNewsList2(@Path("channelId") String channelId,
                                     @Path("start") int start,
                                     @Path("end") int end);

}
