package com.cn.guojinhu.dota2book.ui.main;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/3.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    private MainContract.View mMainView;

    public MainPresenter(MainContract.View mainView) {
        mMainView = mainView;

        mMainView = checkNotNull(mMainView, "mMainView cannot be null!");

        mMainView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
