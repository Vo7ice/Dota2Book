package com.cn.guojinhu.dota2book.ui.heroes;

import android.content.Context;

import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;
import com.cn.guojinhu.dota2book.bean.Heroes;

import java.util.List;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroesContact {
    public interface View extends BaseView<Presenter> {
    }

    public interface Presenter extends BasePresenter {
        List<Heroes.Hero> getHeroList();
    }
}
