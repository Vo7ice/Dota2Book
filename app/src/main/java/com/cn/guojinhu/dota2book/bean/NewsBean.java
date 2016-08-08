package com.cn.guojinhu.dota2book.bean;

import java.util.List;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class NewsBean {
    public List<News> mNewsList;

    public class News{
        public List<Ads> mAdList;
        public String alias;
        public String boardid;
        public String cid;
        public String digest;
        public String docid;
        public List<ImgExtra> mImgExtraList;
        public String ename;
        public int hasAD;
        public boolean hasCover;
        public int hasHead;
        public boolean hasIcon;
        public int hasImg;
        public String imgsrc;
        public String lmodify;
        public int order;
        public String photosetId;
        public int priority;
        public String ptime;
        public int replyCount;
        public String skipId;
        public String skipType;
        public String source;
        public String template;
        public String title;
        public String tname;
        public String url;
        public String url_3w;
        public int votecount;

        public class Ads {
            public String imgsrc;
            public String subtitle;
            public String tag;
            public String title;
            public String url;
        }

        public class ImgExtra {
            public String imgsrc;
        }
    }

}
