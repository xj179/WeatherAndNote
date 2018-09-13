package com.lxj.note.myth.vest;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VestBean implements Serializable {

    @SerializedName("0")
    private String tag0 ;
    @SerializedName("1")
    private String tag1 ;
    @SerializedName("2")
    private String tag2 ;
    @SerializedName("3")
    private String tag3 ;
    @SerializedName("4")
    private String tag4 ;
    @SerializedName("5")
    private String tag5 ;
    @SerializedName("6")
    private String tag6 ;
    @SerializedName("7")
    private String tag7 ;

    private String id ;
    private String url ;
    private String type ;
    private String show_url ;
    private String appid ;
    private String comment ;
    private String createAt ;
    private String updateAt ;

    public String getTag0() {
        return tag0;
    }

    public void setTag0(String tag0) {
        this.tag0 = tag0;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }

    public String getTag5() {
        return tag5;
    }

    public void setTag5(String tag5) {
        this.tag5 = tag5;
    }

    public String getTag6() {
        return tag6;
    }

    public void setTag6(String tag6) {
        this.tag6 = tag6;
    }

    public String getTag7() {
        return tag7;
    }

    public void setTag7(String tag7) {
        this.tag7 = tag7;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "VestBean{" +
                "tag0='" + tag0 + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", tag3='" + tag3 + '\'' +
                ", tag4='" + tag4 + '\'' +
                ", tag5='" + tag5 + '\'' +
                ", tag6='" + tag6 + '\'' +
                ", tag7='" + tag7 + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", show_url='" + show_url + '\'' +
                ", appid='" + appid + '\'' +
                ", comment='" + comment + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}
