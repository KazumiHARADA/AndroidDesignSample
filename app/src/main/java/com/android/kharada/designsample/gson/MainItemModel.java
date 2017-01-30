package com.android.kharada.designsample.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haradakazumi on 2017/01/29.
 */

public class MainItemModel {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("activityName")
    private String mActivityName;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getActivityName() {
        return mActivityName;
    }

    public void setActivityName(String activityName) {
        this.mActivityName = activityName;
    }
}
