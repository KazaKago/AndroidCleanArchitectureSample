package com.ignis.android_cleanarchitecture.domain.model;

import io.realm.RealmObject;

/**
 * Location Model
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class LocationModel extends RealmObject {

    //地方名（例・九州地方）
    private String area;
    //都道府県名（例・福岡県）
    private String pref;
    //1次細分区名（例・八幡）
    private String city;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
