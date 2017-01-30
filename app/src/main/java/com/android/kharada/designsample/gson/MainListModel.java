package com.android.kharada.designsample.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by haradakazumi on 2017/01/29.
 */

public class MainListModel {

    @SerializedName("list")
    private List<MainItemModel> list;

    public List<MainItemModel> getList() {
        return list;
    }

    public void setList(List<MainItemModel> list) {
        this.list = list;
    }
}
