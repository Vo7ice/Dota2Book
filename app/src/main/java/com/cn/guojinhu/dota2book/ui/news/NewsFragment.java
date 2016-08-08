package com.cn.guojinhu.dota2book.ui.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.R;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class NewsFragment extends BaseFragment {

    private View mRootView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initViews() {
        mRootView = getRootView();
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.news_tab_layout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.news_viewpager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
