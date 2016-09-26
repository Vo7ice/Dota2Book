package com.cn.guojinhu.dota2book.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by houqiqi on 2016/9/22.
 */

public class OtherUtils {
    public static void close(Closeable closeable){
        if(null!=closeable){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
