package com.cn.guojinhu.dota2book.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by guojin.hu on 2016/8/3.
 */

public abstract class BaseActivity extends SwipeBackActivity implements IInitialize {

    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    protected void disableSwipeBack() {
        mSwipeBackLayout.setEdgeTrackingEnabled(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
        initListener();
        initData();
    }

}
