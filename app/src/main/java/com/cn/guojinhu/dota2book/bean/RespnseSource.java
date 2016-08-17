package com.cn.guojinhu.dota2book.bean;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class RespnseSource {
    private Map<String, NewsDetail> detail = new HashMap<>();

    public Map<String, NewsDetail> getDetail() {
        return detail;
    }

    public void setDetail(Map<String, NewsDetail> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "RespnseSource{" +
                "detail=" + detail +
                '}';
    }
}
