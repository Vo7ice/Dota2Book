package com.cn.guojinhu.dota2book.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        GlideBuilder builder = new GlideBuilder(context);
        request.placeholder(loadingImg);
        request.error(errorImg);
        request.thumbnail(0.1f)//用原图的1/10作为缩略图,优先加载缩略图
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.transform(new GlideRoundTransform(context))
                //.dontAnimate()//不显示动画
                .crossFade()//渐入渐出
                .into(imageView);
    }

    /*显示圆角图片*/
    public static void displayRoundImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .placeholder(context.getDrawable(R.drawable.ic_photo_size_select_actual_black_24dp))
                .error(context.getDrawable(R.drawable.ic_photo_size_select_actual_black_24dp))
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(context, 10))
                .into(imageView);
    }

    /*显示圆形图片*/
    public static void displayCircleImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url)
                .placeholder(context.getDrawable(R.drawable.ic_photo_size_select_actual_black_24dp))
                .error(context.getDrawable(R.drawable.ic_photo_size_select_actual_black_24dp))
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

}
