package com.cn.guojinhu.dota2book.ui.news.newsdetail;

import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class NewsDetailContact {

    public interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void loadNews(String docid);
    }
}
