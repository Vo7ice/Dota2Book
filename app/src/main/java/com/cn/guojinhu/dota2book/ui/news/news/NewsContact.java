package com.cn.guojinhu.dota2book.ui.news.news;

import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;
import com.cn.guojinhu.dota2book.bean.Channel;
import com.cn.guojinhu.dota2book.bean.News;
import com.cn.guojinhu.dota2book.bean.NewsBean;
import com.cn.guojinhu.dota2book.ui.main.MainContract;

import java.util.List;
import java.util.Map;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class NewsContact {

    public interface View extends BaseView<Presenter> {

        void showUI(List<BaseFragment> fragments, List<String> fragmentTitles);

    }

    public interface Presenter extends BasePresenter {
        void loadChannel();

        void loadNewsList(Channel mChannel, int pageIndex);

        void openNews(String docid);

        void openPhotoSet(String photosetId);
    }

    public interface ViewList extends BaseView<Presenter> {

        void showProgress();

        void hideProgress();

        void refreshUI(List<News> mNewsList);

        void showErrorMessage();

        void openPhotoSet(String photosetId);

        void openNews(String news);
    }
}
