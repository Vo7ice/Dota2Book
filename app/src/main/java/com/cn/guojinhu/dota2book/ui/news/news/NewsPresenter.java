package com.cn.guojinhu.dota2book.ui.news.news;

import android.text.TextUtils;
import android.util.Log;

import com.cn.guojinhu.dota2book.IService.INewsService;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Channel;
import com.cn.guojinhu.dota2book.bean.ChannelSettings;
import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.cn.guojinhu.dota2book.commons.Apis;
import com.cn.guojinhu.dota2book.ui.news.newslist.NewsListFragment;
import com.cn.guojinhu.dota2book.utils.ServiceGenerator;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class NewsPresenter implements NewsContact.Presenter {

    private NewsContact.View mNewsView = null;

    private NewsContact.ViewList mNewsListView = null;

    private static final int TYPE0 = 0;
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;

    private static final int PAGE_SIZE = 20;

    public NewsPresenter(NewsContact.View baseView) {
        mNewsView = checkNotNull(baseView, "BaseView cannot be null");
    }

    public NewsPresenter(NewsContact.ViewList baseView) {
        mNewsListView = checkNotNull(baseView, "BaseView cannot be null");
    }

    @Override
    public void start() {

    }

    @Override
    public void loadChannel() {
        Map<String, Channel> channels = ChannelSettings.getChannels();

        List<BaseFragment> mFragments = new ArrayList<>();
        List<String> mFragmentTitles = new ArrayList<>();
        for (Map.Entry<String, Channel> channelEntry : channels.entrySet()) {
            Channel channel = channelEntry.getValue();
            NewsListFragment fragment = NewsListFragment.newInstance(channel);
            mFragments.add(fragment);
            mFragmentTitles.add(channel.channelName);
        }

        mNewsView.showUI(mFragments, mFragmentTitles);
    }

    @Override
    public void loadNewsList(Channel channel, int pageIndex) {
        mNewsListView.showProgress();
        switch (channel.category) {
            case TYPE0:
                INewsService service = ServiceGenerator.createService(INewsService.class, Apis.HOST_TYPE0 + Apis.NORMAL);
                /*service.getNewsList(channel.channelId, pageIndex, (pageIndex+1) * PAGE_SIZE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<NewsBean>>() {
                            @Override
                            public void onCompleted() {
                                Log.d("Vo7ice", "NewsPresenter:onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Vo7ice", "NewsPresenter:onError");
                            }

                            @Override
                            public void onNext(List<NewsBean> newsBeen) {
                                Log.d("Vo7ice", "NewsPresenter:onNext");
                            }
                        });*/
                service.getNewsList(channel.channelId, pageIndex, (pageIndex + 1) * PAGE_SIZE)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                                Log.d("Vo7ice", "NewsPresenter:onResponse" + response.raw().code());
                                Log.d("Vo7ice", "url:" + response.raw().request().urlString());
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.d("Vo7ice", "NewsPresenter:onFailure");
                            }
                        });
                break;
        }
    }
}
