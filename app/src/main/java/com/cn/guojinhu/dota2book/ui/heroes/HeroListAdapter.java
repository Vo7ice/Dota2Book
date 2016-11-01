package com.cn.guojinhu.dota2book.ui.heroes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.guojinhu.dota2book.R;
import com.cn.guojinhu.dota2book.base.BaseAdapter;
import com.cn.guojinhu.dota2book.bean.Hero;
import com.cn.guojinhu.dota2book.commons.Dota2Apis;
import com.cn.guojinhu.dota2book.ui.heroes.gallery.HeroesGalleryActivity;
import com.cn.guojinhu.dota2book.utils.glide.BitmapUtils;

import java.util.List;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class HeroListAdapter extends BaseAdapter<Hero> {
    private static final String TAG = "HeroListAdapter";
    private OnHeroesItemClickListener mListener;

    public HeroListAdapter(List<Hero> t, Context mContext, OnHeroesItemClickListener listener) {
        super(t, mContext);
        mListener = listener;
    }

    //private List<Heroes.Hero> mHeroList;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;

        holder = new HeroHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_heroes_simple, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Hero hero = t.get(position);
        if (holder instanceof HeroHolder) {
            final HeroHolder heroHolder = (HeroHolder) holder;
            heroHolder.text_name.setText(hero.name);
            heroHolder.text_cname.setText(hero.cname);
            /*BitmapUtils.displayRoundImage(mContext,
                    ((HeroHolder) holder).image_avatar, Dota2Apis.BASE_URL + hero.HoverSmall);*/
            BitmapUtils.displayRoundImage(mContext, heroHolder.image_avatar,
                    Dota2Apis.BASE_URL + hero.HoverLarge);

            heroHolder.image_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HeroesGalleryActivity.class);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
//                    if (null != mListener){
//                        mListener.onHeroDetail(hero);
//                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return t.size();
    }

    /*public void setData(List<Heroes.Hero> heroes){

    }

    public void replaceData(List<Heroes.Hero> heroes) {
        setData(heroes);
        notifyDataSetChanged();
    }

    public void addData(List<Heroes.Hero> heroes) {
        mHeroList.addAll(heroes);
        notifyDataSetChanged();
    }*/

    public static class HeroHolder extends RecyclerView.ViewHolder {

        private ImageView image_avatar;
        private TextView text_name, text_cname;

        public HeroHolder(View itemView) {
            super(itemView);
            image_avatar = (ImageView) itemView.findViewById(R.id.image_avatar);
            text_cname = (TextView) itemView.findViewById(R.id.text_cname);
            text_name = (TextView) itemView.findViewById(R.id.text_name);
        }
    }
}
