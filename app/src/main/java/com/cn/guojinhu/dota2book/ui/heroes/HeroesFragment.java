package com.cn.guojinhu.dota2book.ui.heroes;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Heroes;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroesFragment extends BaseFragment implements HeroesContact.View {

    private HeroesPresenter mPresenter;
    private List<Heroes.Hero> mHeroList;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private HeroListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_heroes;
    }

    @Override
    public void setPresenter(HeroesContact.Presenter p) {
        mPresenter = (HeroesPresenter) checkNotNull(p);
    }

    @Override
    public void initViews() {
        View mRootView = getRootView();
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.heroes_recycler_view);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mAdapter = new HeroListAdapter(new ArrayList<Heroes.Hero>(), getActivity());
        mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPresenter = new HeroesPresenter(this);
        mPresenter.start();
        mHeroList = mPresenter.getHeroList(getActivity());
        if (null != mHeroList && !mHeroList.isEmpty()) {
            mAdapter.replaceData(mHeroList);
        }
    }

}
