package com.cn.guojinhu.dota2book.bean;

/**
 * Created by guojin.hu on 2016/9/18.
 */

public class Hero {

    public String name;
    public String cname;
    public String HoverLarge;
    public String HoverSmall;


    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", cname='" + cname + '\'' +
                ", HoverLarge='" + HoverLarge + '\'' +
                ", HoverSmall='" + HoverSmall + '\'' +
                '}';
    }
}
