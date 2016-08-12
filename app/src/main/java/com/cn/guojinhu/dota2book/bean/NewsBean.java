package com.cn.guojinhu.dota2book.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojin.hu on 2016/8/8.
 */

public class NewsBean {
    public List<News> mNewsList = new ArrayList<>();

    public class News {

        public static final int NORMAL_TYPE = 0;
        public static final int PHOTO_TYPE = 1;

        public static final String PHOTO_PATH = "http://c.m.163.com/photo/api/set/";
        public static final String BASE_PATH = "http://c.m.163.com/nc/article/";

        public List<Ads> mAdList;//广告
        public String alias;//别称    
        public String boardid;//板块名字
        public String cid;
        public String digest;
        public String docid;//详情显示页面
        public List<ImgExtra> mImgExtraList;//图片
        public String ename;
        public int hasAD;//是否有广告
        public boolean hasCover;//是否
        public int hasHead;
        public boolean hasIcon;//是否有图标
        public int hasImg;//是否有配图
        public String imgsrc;//配图地址
        public String lmodify;//last modify
        public int order;//
        public String photosetId;//photosetID=54GI0096|71046  可计算出图片请求 url http://c.m.163.com/photo/api/set/0096/71046.json
        public int priority;
        public String ptime;
        public int replyCount;
        public String skipId;
        public String skipType;//photoset表示详显是图片浏览方式
        public String source;
        public String template;
        public String title;
        public String tname;
        public String url;
        public String url_3w;
        public int votecount;

        private String detailUrl;//详情页url

        public class Ads {
            public String imgsrc;//图片
            public String subtitle;//副标题
            public String tag;//推广 视频
            public String title;//标题
            public String url;//url
        }

        public class ImgExtra {
            public String imgsrc;//图片url
        }

        public String getDetailUrl() {
            switch (getDetailType()) {
                case NORMAL_TYPE:
                    detailUrl = BASE_PATH + docid + "/full.html";
                    break;
                case PHOTO_TYPE:
                    int start = photosetId.indexOf("|");
                    String target = photosetId.substring(start - 4);
                    target = target.replace("|", "/");
                    detailUrl = PHOTO_PATH + target + ".json";
                    break;
            }
            return detailUrl;
        }

        public int getDetailType() {
            if (TextUtils.equals(skipType, "photoset")) {
                return PHOTO_TYPE;
            } else {
                return NORMAL_TYPE;
            }
        }
    }

}
