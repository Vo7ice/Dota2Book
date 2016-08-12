package com.cn.guojinhu.dota2book.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class NewsBean {

    @SerializedName("T1348647909107")
    public List<News> mHeadLineNews = new ArrayList<>();

    @SerializedName("T1348648517839")
    public List<News> mEntNews = new ArrayList<>();

    @SerializedName("T1348649079062")
    public List<News> mSportsNews = new ArrayList<>();

    @SerializedName("T1348649580692")
    public List<News> mTechNews = new ArrayList<>();

    @SerializedName("T1348654151579")
    public List<News> mGameNews = new ArrayList<>();


}
