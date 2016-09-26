package com.cn.guojinhu.dota2book.ui.equipments;

import android.content.Context;

import com.cn.guojinhu.dota2book.base.BasePresenter;
import com.cn.guojinhu.dota2book.base.BaseView;
import com.cn.guojinhu.dota2book.bean.Equipments;

import java.util.List;

/**
 * Created by houqiqi on 2016/9/22.
 */

public class EquipmentsContact {
    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends BasePresenter {
        List<Equipments.Equipment> getEquipmentList(Context context);

    }
}
