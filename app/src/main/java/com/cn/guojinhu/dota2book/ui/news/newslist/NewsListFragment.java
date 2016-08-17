package com.cn.guojinhu.dota2book.ui.news.newslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Channel;
import com.cn.guojinhu.dota2book.bean.News;
import com.cn.guojinhu.dota2book.ui.news.news.NewsContact;
import com.cn.guojinhu.dota2book.ui.news.news.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class NewsListFragment extends BaseFragment implements NewsContact.ViewList,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_CHANNEL = "channel";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;
    private NewsPresenter mPresenter;
    private int pageIndex = 0;
    private Channel mChannel;
    private LinearLayoutManager mLayoutManager;
    private OnItemClickListener mListener;

    private static final String DETAIL = "com.cn.guojinhu.newsdetail";
    private static final String PHOTO_SET = "com.cn.guojinhu.photoset";
    private static final String KEY_DETAIL = "key_detail";
    private static final String KEY_IMG = "key_img";
    private static final String KEY_PHOTOSET = "key_photoset";

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
        mListener = new OnItemClickListener() {
            @Override
            public void onNewsDetail(News news) {
                mPresenter.openNews(news);
            }

            @Override
            public void onPhotoSet(News news) {
                mPresenter.openPhotoSet(news.photosetId);
            }

            @Override
            public void onAdsPhotoSet(String photosetId) {
                mPresenter.openPhotoSet(photosetId);
            }
        };
    }

    @Override
    public void initData() {
        mChannel = getArguments().getParcelable(KEY_CHANNEL);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);//下拉刷新

        mAdapter = new NewsListAdapter(new ArrayList<News>(), getActivity());
        mAdapter.setOnItemClickListener(mListener);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {

                    //加载更多
                    mSwipeRefreshLayout.setRefreshing(true);
                    Log.d("Vo7ice","more-->"+pageIndex);
                    mPresenter.loadNewsList(mChannel, pageIndex);
                }
            }
        });

        mPresenter = new NewsPresenter(this);
        mPresenter.loadNewsList(mChannel, pageIndex);
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
    public void refreshUI(List<News> mNewsList) {
        Log.d("Vo7ice", "listfragment-->" + mNewsList.size());
        if (pageIndex == 0) {
            mAdapter.replaceData(mNewsList);
        }else{
            mAdapter.addData(mNewsList);
        }
        pageIndex++;
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getRootView(), R.string.network_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openPhotoSet(String photosetId) {
        Intent intent = new Intent(PHOTO_SET);
        intent.putExtra(KEY_PHOTOSET,photosetId);
        getActivity().startActivity(intent);
    }

    @Override
    public void openNews(News news) {
        Intent intent = new Intent(DETAIL);
        intent.putExtra(KEY_DETAIL,news.docid);
        intent.putExtra(KEY_IMG,news.imgsrc);
        getActivity().startActivity(intent);
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        mPresenter.loadNewsList(mChannel, pageIndex);
    }

}
