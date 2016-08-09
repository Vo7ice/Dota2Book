package com.cn.guojinhu.dota2book.utils;

import com.cn.guojinhu.dota2book.commons.Apis;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * Created by guojin.hu on 2016/8/8.
 */

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = Builder(Apis.HOST_TYPE0).client(new OkHttpClient()).build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String url) {
        Retrofit retrofit = Builder(url).client(new OkHttpClient()).build();
        return retrofit.create(serviceClass);
    }

    private static Retrofit.Builder Builder(String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
}
