package com.cn.guojinhu.dota2book.ui.heroes;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseFragment;
import com.cn.guojinhu.dota2book.bean.Hero;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroesFragment extends BaseFragment implements HeroesContact.View {

    private static final String TAG="HeroesFragment";

    private HeroesPresenter mPresenter;
    private List<Hero> mHeroList;
    private RecyclerView mRecyclerView;
    //private GridLayoutManager mLayoutManager;
    private LinearLayoutManager mLayoutManager;
    private HeroListAdapter mAdapter;
    private OnHeroesItemClickListener mListener;

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
        mListener = new OnHeroesItemClickListener() {
            @Override
            public void onHeroDetail(Hero hero) {
                mPresenter.showHeroDetail(hero);
            }
        };
    }

    @Override
    public void initData() {
        mAdapter = new HeroListAdapter(new ArrayList<Hero>(), getActivity(),mListener);
        //mLayoutManager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mPresenter = new HeroesPresenter(this);
        getHeroList();
        mPresenter.start();
    }

    private void getHeroList(){

        Observable.just(mPresenter.getHeroList(getActivity()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Hero>>() {
                    @Override
                    public void call(List<Hero> heroes) {
                        mHeroList=heroes;
                        if (null != heroes && !heroes.isEmpty()) {
                            mAdapter.replaceData(heroes);
                        }
                        Log.i("hqq","getHeroList"+"\t"+mHeroList.size());
                    }
                });
    }

    @Override
    public void openHeroDetail(Hero hero) {

    }
}
