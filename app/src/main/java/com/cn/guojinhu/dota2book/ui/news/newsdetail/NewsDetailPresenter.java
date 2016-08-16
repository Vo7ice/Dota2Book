package com.cn.guojinhu.dota2book.ui.news.newsdetail;

import android.util.Log;

import com.cn.guojinhu.dota2book.IService.INewsDetail;
import com.cn.guojinhu.dota2book.bean.NewsDetail;
import com.cn.guojinhu.dota2book.bean.RespnseSource;
import com.cn.guojinhu.dota2book.commons.Apis;
import com.cn.guojinhu.dota2book.utils.ServiceGenerator;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class NewsDetailPresenter implements NewsDetailContact.Presenter {
    private NewsDetailContact.View baseView;
    private INewsDetail service;

    public NewsDetailPresenter(NewsDetailContact.View baseView) {
        this.baseView = checkNotNull(baseView);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadNews(String docid) {
        service = ServiceGenerator.customCreateService(INewsDetail.class, Apis.HOST_TYPE0 + Apis.NEWS_DETAIL);
        service.getNewsDetail(docid).enqueue(new Callback<NewsDetail>() {
            @Override
            public void onResponse(Response<NewsDetail> response, Retrofit retrofit) {
                    /*String source = response.body().string();
                    Log.d("Vo7ice","source:"+source);
                    Gson gson = new Gson();
                    RespnseSource respnseSource = gson.fromJson(source, RespnseSource.class);
                    Log.d("Vo7ice","response:"+respnseSource.toString());
                    Map<String, NewsDetail> detail = respnseSource.getDetail();
                    if (null != detail) {
                        for (String s : detail.keySet()) {
                            Log.d("Vo7ice", "key-->" + s);
                        }
                    }*/
                NewsDetail body = response.body();
                Log.d("Vo7ice", "body-->" + (body == null));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Vo7ice", "onFailure");
            }
        });
    }
}
