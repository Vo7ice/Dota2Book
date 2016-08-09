package com.cn.guojinhu.dota2book.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class Channel implements Parcelable {
    public String channelId;
    public String channelName;
    /**
     * 0:http://c.m.163.com/nc/article/list/
     * 1:http://c.3g.163.com/nc/article/list/
     * 2:http://c.3g.163.com/nc/article/headline/
     * 3.图片 热点
     */
    public int category;
    public String url;
    public String hweburl;

    public Channel(String channelId, String channelName,  int category, String url, String hweburl) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.category = category;
        this.url = url;
        this.hweburl = hweburl;
    }

    protected Channel(Parcel in) {
        channelId = in.readString();
        channelName = in.readString();
        category = in.readInt();
        url = in.readString();
        hweburl = in.readString();
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(channelId);
        parcel.writeString(channelName);
        parcel.writeInt(category);
        parcel.writeString(url);
        parcel.writeString(hweburl);
    }
}
