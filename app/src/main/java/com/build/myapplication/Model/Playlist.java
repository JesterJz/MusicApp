package com.build.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable{

    @SerializedName("IDPlayList")
    @Expose
    private String iDPlayList;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhNen")
    @Expose
    private String hinhNen;
    @SerializedName("HinhIcon")
    @Expose
    private String hinhIcon;
    @SerializedName("IDChuDe")
    @Expose
    private String iDChuDe;

    public String getIDPlayList() {
        return iDPlayList;
    }

    public void setIDPlayList(String iDPlayList) {
        this.iDPlayList = iDPlayList;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhNen() {
        return hinhNen;
    }

    public void setHinhNen(String hinhNen) {
        this.hinhNen = hinhNen;
    }

    public String getHinhIcon() {
        return hinhIcon;
    }

    public void setHinhIcon(String hinhIcon) {
        this.hinhIcon = hinhIcon;
    }

    public String getIDChuDe() {
        return iDChuDe;
    }

    public void setIDChuDe(String iDChuDe) {
        this.iDChuDe = iDChuDe;
    }

}