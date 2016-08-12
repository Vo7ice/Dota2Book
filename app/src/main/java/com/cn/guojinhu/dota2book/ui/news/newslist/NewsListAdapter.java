package com.cn.guojinhu.dota2book.ui.news.newslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.bean.News;
import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.cn.guojinhu.dota2book.utils.BitmapUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/10.
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<News> mNewsList;
    private Context mContext;

    private static final int NORMAL = 0;
    private static final int PHOTO_SET = 1;
    private static final int ADS = 2;

    public NewsListAdapter(List<News> newsList, Context context) {
        this.mNewsList = newsList;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case NORMAL:
                holder = new NewsListHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_news_list_normal, parent, false));
                break;
            case PHOTO_SET:
                holder = new PhotoSetHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_news_list_photoset, parent, false));
                break;
            default:
                holder = new NewsListHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_news_list_normal, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final News news = mNewsList.get(position);
        if (viewHolder instanceof NewsListHolder) {
            final NewsListHolder holder = (NewsListHolder) viewHolder;
            BitmapUtils.display(mContext, holder.image_news_list, news.imgsrc);
            holder.text_news_list_title.setText(news.title);
            holder.text_news_list_digest.setText(news.digest);
        } else if (viewHolder instanceof PhotoSetHolder) {
            final PhotoSetHolder holder = (PhotoSetHolder) viewHolder;
            holder.text_news_list_title.setText(news.title);
            BitmapUtils.display(mContext,holder.imageExtra0,news.imgsrc);
            BitmapUtils.display(mContext, holder.imageExtra1, news.mImgExtraList.get(0).imgsrc);
        }
    }

    @Override
    public int getItemViewType(int position) {
        final News news = mNewsList.get(position);
        if (!news.mAdList.isEmpty()) {
            return ADS;
        } else if (!news.mImgExtraList.isEmpty() && news.mAdList.isEmpty()) {
            return PHOTO_SET;
        } else {
            return NORMAL;
        }
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
    public void replaceData(List<News> newsList) {
        setData(newsList);
        notifyDataSetChanged();
    }

    public void setData(List<News> newsList) {
        mNewsList = checkNotNull(newsList);
    }

    public void addData(List<News> newsList) {
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }


    public static class NewsListHolder extends RecyclerView.ViewHolder {
        private ImageView image_news_list;
        private TextView text_news_list_title, text_news_list_digest;

        public NewsListHolder(View itemView) {
            super(itemView);
            image_news_list = (ImageView) itemView.findViewById(R.id.image_news_list);
            text_news_list_digest = (TextView) itemView.findViewById(R.id.text_news_list_digest);
            text_news_list_title = (TextView) itemView.findViewById(R.id.text_news_list_title);
        }
    }

    public static class PhotoSetHolder extends RecyclerView.ViewHolder {
        private ImageView imageExtra0, imageExtra1;
        private TextView text_news_list_title;

        public PhotoSetHolder(View itemView) {
            super(itemView);
            imageExtra0 = (ImageView) itemView.findViewById(R.id.image_extra0);
            imageExtra1 = (ImageView) itemView.findViewById(R.id.image_extra1);
            text_news_list_title = (TextView) itemView.findViewById(R.id.text_news_list_title);
        }
    }

    public static class AdsHolder extends RecyclerView.ViewHolder {

        public AdsHolder(View itemView) {
            super(itemView);

        }
    }


}
