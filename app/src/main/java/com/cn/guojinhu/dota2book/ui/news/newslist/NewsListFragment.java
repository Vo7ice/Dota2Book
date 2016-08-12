package com.cn.guojinhu.dota2book.ui.news.newslist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Channel;
import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.cn.guojinhu.dota2book.ui.news.news.NewsContact;
import com.cn.guojinhu.dota2book.ui.news.news.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class NewsListFragment extends BaseFragment implements NewsContact.ViewList {

    private static final String KEY_CHANNEL = "channel";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;
    private NewsPresenter mPresenter;
    private int pageIndex = 0;

    public static NewsListFragment newInstance(Channel channel) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle data = new Bundle();
        data.putParcelable(KEY_CHANNEL, channel);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void initViews() {
        View mRootView = getRootView();
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.news_swipe_refresh_layout);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.news_recycler_view);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Channel channel = getArguments().getParcelable(KEY_CHANNEL);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        mAdapter = new NewsListAdapter(new ArrayList<NewsBean.News>(), getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        mPresenter = new NewsPresenter(this);
        mPresenter.loadNewsList(channel, pageIndex);
    }

    @Override
    public void setPresenter(NewsContact.Presenter p) {
        mPresenter = (NewsPresenter) checkNotNull(p);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshUI(List<NewsBean.News> mNewsList) {
        mAdapter.replaceData(mNewsList);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getRootView(), R.string.network_error, Snackbar.LENGTH_LONG).show();
    }
}
