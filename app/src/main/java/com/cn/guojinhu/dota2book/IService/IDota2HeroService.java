package com.cn.guojinhu.dota2book.IService;

import android.graphics.Bitmap;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public interface IDota2HeroService {
    @GET()
    Observable<Bitmap> getHeroBitmap(@Path("name") String name);

}
