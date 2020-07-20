package com.build.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ChudeAll {

@SerializedName("Chude_current_day")
@Expose
private List<Chude> chudeCurrentDay = null;
@SerializedName("Chude")
@Expose
private List<Chude> chude = null;

public List<Chude> getChudeCurrentDay() {
return chudeCurrentDay;
}

public void setChudeCurrentDay(List<Chude> chudeCurrentDay) {
this.chudeCurrentDay = chudeCurrentDay;
}

public List<Chude> getChude() {
return chude;
}

public void setChude(List<Chude> chude) {
this.chude = chude;
}

}
