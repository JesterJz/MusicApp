package com.build.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chude implements Serializable {

@SerializedName("IDChuDe")
@Expose
private String iDChuDe;
@SerializedName("TenChuDe")
@Expose
private String tenChuDe;
@SerializedName("HinhChuDe")
@Expose
private String hinhChuDe;

public String getIDChuDe() {
return iDChuDe;
}

public void setIDChuDe(String iDChuDe) {
this.iDChuDe = iDChuDe;
}

public String getTenChuDe() {
return tenChuDe;
}

public void setTenChuDe(String tenChuDe) {
this.tenChuDe = tenChuDe;
}

public String getHinhChuDe() {
return hinhChuDe;
}

public void setHinhChuDe(String hinhChuDe) {
this.hinhChuDe = hinhChuDe;
}


}