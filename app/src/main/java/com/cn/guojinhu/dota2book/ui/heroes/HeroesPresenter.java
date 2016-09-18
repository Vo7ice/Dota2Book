package com.cn.guojinhu.dota2book.ui.heroes;

import com.cn.guojinhu.dota2book.bean.Heroes;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroesPresenter implements HeroesContact.Presenter {

    private HeroesContact.View baseView;

    public HeroesPresenter(HeroesContact.View baseView) {
        this.baseView = checkNotNull(baseView);
    }

    @Override
    public void start() {

    }

    public List<Heroes.Hero> getHeroList() {
        return null;
    }
}
