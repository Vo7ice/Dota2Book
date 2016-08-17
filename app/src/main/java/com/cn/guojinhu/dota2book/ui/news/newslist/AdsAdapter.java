package com.cn.guojinhu.dota2book.ui.news.newslist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.bean.News;
import com.cn.guojinhu.dota2book.utils.BitmapUtils;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class AdsAdapter extends PagerAdapter {

    private News mNews;
    private Context mContext;
    private OnItemClickListener mListener;

    public AdsAdapter(News news, Context context) {
        this.mNews = news;
        this.mContext = context;
    }

    public AdsAdapter(News news, Context context, OnItemClickListener listener) {
        this.mNews = news;
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mNews.mAdList.size() + 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ads, container, false);
        ImageView image_pic = (ImageView) view.findViewById(R.id.image_pic);
        TextView text_ads_title = (TextView) view.findViewById(R.id.text_ads_title);
        if (position == 0) {
            BitmapUtils.display(mContext, image_pic, mNews.imgsrc);
            text_ads_title.setText(mNews.title);
        } else {
            BitmapUtils.display(mContext, image_pic, mNews.mAdList.get(position - 1).imgsrc);
            text_ads_title.setText(mNews.mAdList.get(position - 1).title);
        }
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Vo7ice", "ViewPager:onClick-->" + position);
                if (null != mListener && position == 0) {
                    if (mNews.mImgExtraList.isEmpty()) {
                        mListener.onNewsDetail(mNews);
                    } else {
                        mListener.onPhotoSet(mNews);
                    }
                } else if (null != mListener) {
                    mListener.onAdsPhotoSet(mNews.mAdList.get(position - 1).url);
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
