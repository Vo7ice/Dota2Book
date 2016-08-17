package com.cn.guojinhu.dota2book.bean;

import java.util.List;

/**
 * Created by guojin.hu on 2016/8/16.
 */

public class NewsDetail {
//    public List apps;
//    public List boboList;
    public String body;
    public String digest;
    public String dkeys;
    public String docid;
    public String ec;
    public boolean hasNext;
//    public List huati;
//    public List img;
//    public List link;
    public boolean picnews;
    public String ptime;
//    public List relative_sys;
    public String replyBoard;
    public long replyCount;
    public String shareLink;
    public String source;
    public String title;

    @Override
    public String toString() {
        return "NewsDetail{" +
                "body='" + body + '\'' +
                ", digest='" + digest + '\'' +
                ", dkeys='" + dkeys + '\'' +
                ", docid='" + docid + '\'' +
                ", ec='" + ec + '\'' +
                ", hasNext=" + hasNext +
                ", picnews=" + picnews +
                ", ptime='" + ptime + '\'' +
                ", replyBoard='" + replyBoard + '\'' +
                ", replyCount=" + replyCount +
                ", shareLink='" + shareLink + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
