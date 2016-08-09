package com.cn.guojinhu.dota2book.ui.news.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.bean.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class NewsFragment extends BaseFragment implements NewsContact.View {

    private View mRootView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewsPageAdapter mAdapter;
    private NewsPresenter mPresenter;

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
        mAdapter = new NewsPageAdapter(getChildFragmentManager(),
                new ArrayList<BaseFragment>(), new ArrayList<String>());//需要子类管理器
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mPresenter = new NewsPresenter(this);
        mPresenter.loadChannel();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setPresenter(NewsContact.Presenter p) {
        mPresenter = (NewsPresenter) checkNotNull(p);
    }

    @Override
    public void showUI(List<BaseFragment> fragments, List<String> fragmentTitles) {
        mAdapter.clear();
        mTabLayout.removeAllTabs();


        for (String title : fragmentTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
            //Log.d("Vo7ice", "tabs-->" + mTabLayout.getTabCount());
        }
        //先添加tab后更新数据,不然tab数量会增多
        mAdapter.replaceData(fragments, fragmentTitles);
        //Log.d("Vo7ice", "fragmentTitles-->"+fragmentTitles.size()+",tabs-->" + mTabLayout.getTabCount());
        //mViewPager.setOffscreenPageLimit(fragments.size() - 1);
    }
}
