package com.ignis.android_cleanarchitecture.domain.model;

import io.realm.RealmObject;

/**
 * Description Model
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class DescriptionModel extends RealmObject {

    //天気概況文
    private String text;
    //天気概況文の発表時刻
    private String publicTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

}
