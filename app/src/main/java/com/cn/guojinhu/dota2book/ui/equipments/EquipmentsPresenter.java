package com.cn.guojinhu.dota2book.ui.equipments;

import android.content.Context;

import com.cn.guojinhu.dota2book.bean.Equipments;
import com.cn.guojinhu.dota2book.utils.JsonUtils;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by houqiqi on 2016/9/22.
 */

public class EquipmentsPresenter implements EquipmentsContact.Presenter {

    public EquipmentsContact.View baseView;

    public EquipmentsPresenter(EquipmentsContact.View baseView) {
        this.baseView=checkNotNull(baseView);
    }

    @Override
    public List<Equipments.Equipment> getEquipmentList(Context context) {
        try {
            return JsonUtils.getEquipmentFromAssets(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void start() {

    }
}
