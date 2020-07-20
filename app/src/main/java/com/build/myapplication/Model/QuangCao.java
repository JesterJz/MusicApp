package com.build.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuangCao implements Serializable{

@SerializedName("IDAds")
@Expose
private String iDAds;
@SerializedName("HinhAnhAds")
@Expose
private String hinhAnhAds;
@SerializedName("NoidungAds")
@Expose
private String noidungAds;
@SerializedName("IDBaiHat")
@Expose
private String iDBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;

public String getIDAds() {
return iDAds;
}

public void setIDAds(String iDAds) {
this.iDAds = iDAds;
}

public String getHinhAnhAds() {
return hinhAnhAds;
}

public void setHinhAnhAds(String hinhAnhAds) {
this.hinhAnhAds = hinhAnhAds;
}

public String getNoidungAds() {
return noidungAds;
}

public void setNoidungAds(String noidungAds) {
this.noidungAds = noidungAds;
}

public String getIDBaiHat() {
return iDBaiHat;
}

public void setIDBaiHat(String iDBaiHat) {
this.iDBaiHat = iDBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getHinhBaiHat() {
return hinhBaiHat;
}

public void setHinhBaiHat(String hinhBaiHat) {
this.hinhBaiHat = hinhBaiHat;
}

}