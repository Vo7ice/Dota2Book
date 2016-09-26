package com.cn.guojinhu.dota2book.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guojin.hu on 2016/8/3.
 */

public abstract class BaseFragment extends Fragment implements IInitialize {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int id = getLayoutId();

        mRootView = inflater.inflate(id, null);

        return mRootView;
    }

    protected abstract int getLayoutId();

    public View getRootView() {
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initViews();
        this.initListener();
        this.initData();
    }
}
