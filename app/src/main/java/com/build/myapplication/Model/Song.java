package com.build.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

@SerializedName("IDBaiHat")
@Expose
private String iDBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("HinhBaiHat")
@Expose
private String hinhBaiHat;
@SerializedName("CaSi")
@Expose
private String caSi;
@SerializedName("LinkBaiHat")
@Expose
private String linkBaiHat;
@SerializedName("LuotThich")
@Expose
private String luotThich;

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

public String getCaSi() {
return caSi;
}

public void setCaSi(String caSi) {
this.caSi = caSi;
}

public String getLinkBaiHat() {
return linkBaiHat;
}

public void setLinkBaiHat(String linkBaiHat) {
this.linkBaiHat = linkBaiHat;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

}