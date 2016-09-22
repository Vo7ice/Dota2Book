package com.cn.guojinhu.dota2book.ui.heroes;

import android.content.Context;
import android.util.Log;

import com.cn.guojinhu.dota2book.bean.Heroes;
import com.cn.guojinhu.dota2book.utils.JsonUtils;

import java.io.IOException;
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

    public List<Heroes.Hero> getHeroList(Context context) {
        try {
            return JsonUtils.getHeroesFromAssets(context);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Vo7ice","error:"+e.getMessage());
        }
        return null;
    }

}
