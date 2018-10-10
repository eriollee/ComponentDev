package com.example.admin.component.module.recommand;



import com.example.admin.component.module.BaseModel;

import java.util.ArrayList;

/**
 * Created by mycomputer on 2017/4/3.
 */

public class RecommandBodyValue extends BaseModel {

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    //视频专用
//    public String thumb;
//    public String resource;
//    public String resourceID;
//    public String adid;
//    public ArrayList<Monitor> startMonitor;
//    public ArrayList<Monitor> middleMonitor;
//    public ArrayList<Monitor> endMonitor;
//    public String clickUrl;
//    public ArrayList<Monitor> clickMonitor;
//    public EMEvent event;


    @Override
    public String toString() {
        return "RecommandBodyValue{" +
                "type=" + type +
                ", logo='" + logo + '\'' +
                ", title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", price='" + price + '\'' +
                ", text='" + text + '\'' +
                ", site='" + site + '\'' +
                ", from='" + from + '\'' +
                ", zan='" + zan + '\'' +
                ", url=" + url +
                '}';
    }
}

