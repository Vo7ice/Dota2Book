package com.cn.guojinhu.dota2book.ui.heroes;

import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG="HeroesFragment";

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

        new GetHeroListTask().execute();
        mPresenter = new HeroesPresenter(this);
        mPresenter.start();
    }

    private class GetHeroListTask extends AsyncTask<Void,Void,List<Heroes.Hero>>{

        @Override
        protected List<Heroes.Hero> doInBackground(Void... params) {
            return mPresenter.getHeroList(getContext().getApplicationContext());
        }

        @Override
        protected void onPostExecute(List<Heroes.Hero> heros) {
            mHeroList=heros;
            if (null != heros && !heros.isEmpty()) {
                mAdapter.replaceData(heros);
            }
            Log.i(TAG,"get heros success");
        }
    }

}
