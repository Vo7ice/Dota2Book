package com.cn.guojinhu.dota2book.IService;

import com.cn.guojinhu.dota2book.bean.NewsDetail;
import com.cn.guojinhu.dota2book.utils.Docid;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public interface INewsDetail {

    @Docid("docid")
    @GET("{docid}/full.html")
    Call<NewsDetail> getNewsDetail(@Path("docid") String docid);
}
