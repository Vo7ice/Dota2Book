package com.cn.guojinhu.dota2book.ui.news.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.guojinhu.dota2book.base.BaseFragment;

import java.util.List;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class NewsPageAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;
    private List<String> mFragmentTitles;

    public NewsPageAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> fragmentTitles) {
        super(fm);
        mFragments = fragments;
        mFragmentTitles = fragmentTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentTitles.size();
    }

    //显示tab上的字
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void clear(){
        mFragmentTitles.clear();
        mFragments.clear();
    }

    public void replaceData(List<BaseFragment> fragments, List<String> fragmentTitles) {
        mFragments = fragments;
        mFragmentTitles = fragmentTitles;
        notifyDataSetChanged();
    }
}
