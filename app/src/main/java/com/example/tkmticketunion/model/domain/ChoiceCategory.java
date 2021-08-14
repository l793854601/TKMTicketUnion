package com.example.tkmticketunion.model.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 精选分类
 */
public class ChoiceCategory {

    @SerializedName("type")
    private int type;

    @SerializedName("favorites_id")
    private long favoritesId;

    @SerializedName("favorites_title")
    private String favoritesTitle;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(long favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getFavoritesTitle() {
        return favoritesTitle;
    }

    public void setFavoritesTitle(String favoritesTitle) {
        this.favoritesTitle = favoritesTitle;
    }

    @Override
    public String toString() {
        return "ChoiceCategory{" +
                "type=" + type +
                ", favoritesId=" + favoritesId +
                ", favoritesTitle='" + favoritesTitle + '\'' +
                '}';
    }
}
