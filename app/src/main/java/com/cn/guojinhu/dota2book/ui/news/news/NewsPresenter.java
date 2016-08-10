package com.cn.guojinhu.dota2book.ui.news.news;

import android.util.Log;

import com.cn.guojinhu.dota2book.IService.INewsService;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Channel;
import com.cn.guojinhu.dota2book.bean.ChannelSettings;
import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.cn.guojinhu.dota2book.commons.Apis;
import com.cn.guojinhu.dota2book.ui.news.newslist.NewsListFragment;
import com.cn.guojinhu.dota2book.utils.ServiceGenerator;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private INewsService service;

    private static final int TYPEM = 0;
    private static final int TYPE3G = 1;
    private static final int TYPEHEADLINE = 2;
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
        List<Channel> channels = ChannelSettings.getChannels();

        List<BaseFragment> mFragments = new ArrayList<>();
        List<String> mFragmentTitles = new ArrayList<>();
        for (Channel channel : channels) {
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
            case TYPEM:
                service = ServiceGenerator.createService(INewsService.class, Apis.HOST_TYPE0 + Apis.NORMAL);
                getNewsList(channel, pageIndex);
            case TYPE3G:
                service = ServiceGenerator.createService(INewsService.class, Apis.HOST_TYPE1 + Apis.NORMAL);
                getNewsList(channel, pageIndex);
                break;
            case TYPEHEADLINE:
                service = ServiceGenerator.createService(INewsService.class, Apis.HOST_TYPE0 + Apis.HEADLINE);
                getNewsList(channel, pageIndex);
                break;
        }
    }

    /**
     * 网络请求
     *
     * @param channel   channel
     * @param pageIndex default 0
     */
    private void getNewsList(Channel channel, int pageIndex) {
        /*service.getNewsList(channel.channelId, pageIndex, (pageIndex + 1) * PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("Vo7ice", "NewsPresenter:onCompleted");
                        mNewsListView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Vo7ice", "NewsPresenter:onError");
                        mNewsListView.hideProgress();
                        mNewsListView.showErrorMessage();
                    }

                    @Override
                    public void onNext(NewsBean newsBeen) {
                        Log.d("Vo7ice", "NewsPresenter:onNext");
                        mNewsListView.refreshUI(newsBeen.mNewsList);
                    }
                });*/
        service.getNewsList(channel.channelId, pageIndex, (pageIndex + 1) * PAGE_SIZE)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                        Gson mGson = new Gson();
                        try {
                            String string = response.body().string();
                            Log.d("Vo7ice","source:"+string);
                            NewsBean newsBean = mGson.fromJson(string, NewsBean.class);
                            Log.d("Vo7ice", "newsBean:size-->" + newsBean.mNewsList.size());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //mNewsListView.refreshUI(news.mNewsList);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("Vo7ice", "onFailure-->" + t.getMessage());
                    }
                });
    }
}
