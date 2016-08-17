package com.cn.guojinhu.dota2book.IService;

import com.cn.guojinhu.dota2book.bean.NewsDetail;
import com.cn.guojinhu.dota2book.utils.Docid;
import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public interface INewsDetail {

    @GET("{docid}/full.html")
    Call<Map<String, NewsDetail>> getNewsDetail(@Path("docid") String docid );

    @Docid("user")
    @GET("{docid}/full.html")
    Call<NewsDetail> getNewsDetail2(@Path("docid") String docid );
}
