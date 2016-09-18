package com.cn.guojinhu.dota2book.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> t;

    protected Context mContext;

    public BaseAdapter(List<T> t, Context mContext) {
        this.t = t;
        this.mContext = mContext;
    }

    public void setData(List<T> t){
        this.t = checkNotNull(t);
    }

    public void replaceData(List<T> t){
        setData(t);
        notifyDataSetChanged();
    }
    public void addData(List<T> t){
        this.t.addAll(t);
        notifyDataSetChanged();
    }
}
