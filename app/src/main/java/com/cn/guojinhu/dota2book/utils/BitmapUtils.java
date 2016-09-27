package com.cn.guojinhu.dota2book.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.cn.guojinhu.dota2book.R;

/**
 * Created by guojin.hu on 2016/8/12.
 */

public class BitmapUtils {

    public static void display(Context context, ImageView imageView, String url) {
        display(context,
                imageView,
                url,
                R.drawable.ic_photo_size_select_actual_black_24dp,
                R.drawable.ic_photo_size_select_actual_black_24dp);
    }

    private static void display(Context context, ImageView imageView, String url,
                                @DrawableRes int loadingImg, @DrawableRes int errorImg) {
        DrawableTypeRequest<String> request = Glide.with(context).load(url);
        request.placeholder(loadingImg);
        request.error(errorImg);
        request.thumbnail(0.1f)//用原图的1/10作为缩略图,优先加载缩略图
                .fitCenter()
                //.transform(new GlideRoundTransform(context))
                //.dontAnimate()//不显示动画
                .crossFade()//渐入渐出
                .into(imageView);
    }

    public static void displayRoundImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                //.transform(new GlideRoundTransform(context))
                .placeholder(R.drawable.ic_photo_size_select_actual_black_24dp)
//                .error(R.drawable.ic_error)
                .crossFade()
//                .override(300,150)
                .centerCrop()
                .into(imageView);
    }
}