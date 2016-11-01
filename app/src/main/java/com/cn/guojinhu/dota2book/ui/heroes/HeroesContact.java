package com.cn.guojinhu.dota2book.ui.heroes;

import android.content.Context;

import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;
import com.cn.guojinhu.dota2book.bean.Hero;

import java.util.List;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroesContact {
    public interface View extends BaseView<Presenter> {

        void openHeroDetail(Hero hero);
    }

    public interface Presenter extends BasePresenter {
        List<Hero> getHeroList(Context context);

        void showHeroDetail(Hero hero);
    }
}
