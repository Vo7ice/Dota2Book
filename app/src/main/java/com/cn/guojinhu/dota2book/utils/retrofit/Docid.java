package com.cn.guojinhu.dota2book.utils.retrofit;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by guojin.hu on 2016/8/16.
 */

@Target(METHOD)
@Retention(RUNTIME)
public @interface Docid {
    String value() default "data";
}
