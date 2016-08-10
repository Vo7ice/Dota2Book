package com.cn.guojinhu.dota2book.ui.news.newslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cn.guojinhu.dota2book.bean.NewsBean;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/10.
 */

public class NewsListAdapter extends RecyclerView.Adapter {

    private List<NewsBean.News> mNewsList;
    private Context mContext;

    public NewsListAdapter(List<NewsBean.News> newsList, Context context) {
        this.mNewsList = newsList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    /**
     * 更新数据源
     *
     * @param newsList 替换数据源
     */
    public void replaceData(List<NewsBean.News> newsList) {
        setData(newsList);
        notifyDataSetChanged();
    }

    public void setData(List<NewsBean.News> newsList){
        mNewsList = checkNotNull(newsList);
    }

    public static class NewsListHolder extends RecyclerView.ViewHolder {

        public NewsListHolder(View itemView) {
            super(itemView);
        }
    }


}
