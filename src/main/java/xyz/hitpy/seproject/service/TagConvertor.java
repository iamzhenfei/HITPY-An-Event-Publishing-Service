package xyz.hitpy.seproject.service;


public class TagConvertor {
    public static String convert(String tag) {
        tag = tag.replace("xuexi", "学习");
        tag = tag.replace("qinggan", "情感");
        tag = tag.replace("youxi", "游戏");
        tag = tag.replace("yundong", "运动");
        tag = tag.replace("dianying", "电影");
        tag = tag.replace("chifan", "吃饭");
        tag = tag.replace("lvyou", "旅游");
        tag = tag.replace("maimaimai", "买买买");
        tag = tag.replace("qita", "其他");
        tag = tag.replace("shuma", "数码");
        tag = tag.replace("xiju", "戏剧");
        tag = tag.replace("yinyue", "音乐");
        return tag;
    }
}
