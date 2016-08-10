package com.cn.guojinhu.dota2book.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guojin.hu on 2016/8/9.
 */

public class ChannelSettings {

    private static List<Channel> mChannels = new ArrayList<>();

    public static final String TAB_HEADLINE = "headline";
    public static final String TAB_ENT = "ent";
    public static final String TAB_SPORTS = "sports";
    public static final String TAB_TECH = "tech";
    public static final String TAB_GAME = "game";

    static {
        mChannels.add(new Channel("T1348647909107", "头条", 2,
                "http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html", ""));
        mChannels.add(new Channel("T1348648517839", "娱乐", 1,
                "http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html", ""));
        mChannels.add(new Channel("T1348649079062", "体育", 1,
                "http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html", ""));
        mChannels.add(new Channel("T1348649580692", "科技", 0,
                "http://c.m.163.com/nc/article/list/T1348649580692/0-20.html", ""));
        mChannels.add(new Channel("T1348654151579", "游戏", 0,
                "http://c.m.163.com/nc/article/list/T1348654151579/0-20.html", ""));
    }

    public static List<Channel> getChannels() {
        return mChannels;
    }

}
