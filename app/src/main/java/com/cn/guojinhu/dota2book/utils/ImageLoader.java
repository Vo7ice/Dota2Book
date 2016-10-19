package com.cn.guojinhu.dota2book.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.cn.guojinhu.dota2book.bean.Hero;
import com.cn.guojinhu.dota2book.commons.Dota2Apis;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by houqiqi on 2016/10/14.
 */

public class ImageLoader {

    private LruCache<String,Bitmap> mCache;

    private static ImageLoader intance;

    public static ImageLoader getIntance(){
        if(null==intance){
            synchronized (ImageLoader.class){
                if(null==intance){
                    intance=new ImageLoader();
                }
            }
        }
        return intance;
    }

    public ImageLoader() {

        int mexMemory=(int)Runtime.getRuntime().maxMemory();
        mCache=new LruCache<String, Bitmap>(mexMemory/8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };

    }

    public void putBitMapCache(String key,Bitmap bitmap){
        mCache.put(key,bitmap);
    }



    public Bitmap getBitmapByKey(String key){
        return mCache.get(key);
    }


    public Hero getBitmapByHoverLarge(Hero hero){

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(Dota2Apis.BASE_URL + hero.HoverLarge).build();
        Call call = client.newCall(request);
        InputStream is=null;
        Bitmap bitmap=null;

        try {
            is=call.execute().body().byteStream();
            bitmap= BitmapFactory.decodeStream(is);
            if(null!=bitmap) {
                mCache.put(hero.HoverLarge, bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero;
    }
}
