package com.cn.guojinhu.dota2book.ui.main;

import android.view.MenuItem;

import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;

/**
 * Created by guojin.hu on 2016/8/3.
 */

public class MainContract {

    public interface View extends BaseView<Presenter> {
        boolean IsDrawerOpened();

        void switch2News();

        void closeDrawerIfNeeded();
    }

    public interface Presenter extends BasePresenter {
        void manageFragment(MenuItem item);
    }

}
