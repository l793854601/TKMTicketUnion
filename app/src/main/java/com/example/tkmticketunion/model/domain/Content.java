package com.example.tkmticketunion.model.domain;

import com.example.tkmticketunion.base.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Content extends BaseModel {

    @SerializedName("category_id")
    private long categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("click_url")
    private String clickUrl;

    @SerializedName("commission_rate")
    private String commissionRate;

    @SerializedName("coupon_amount")
    private long couponAmount;

    @SerializedName("coupon_click_url")
    private String couponClickUrl;

    @SerializedName("coupon_end_time")
    private String couponEndTime;

    @SerializedName("coupon_info")
    private String couponInfo;

    @SerializedName("coupon_remain_count")
    private long couponRemainCount;

    @SerializedName("coupon_share_url")
    private String couponShareUrl;

    @SerializedName("coupon_start_fee")
    private String couponStartFee;

    @SerializedName("coupon_start_time")
    private String couponStartTime;

    @SerializedName("coupon_total_count")
    private long couponTotalCount;

    @SerializedName("item_description")
    private String itemDescription;

    @SerializedName("item_id")
    private long itemId;

    @SerializedName("level_one_category_id")
    private long levelOneCategoryId;

    @SerializedName("level_one_category_name")
    private String levelOneCategoryName;

    @SerializedName("nick")
    private String nick;

    @SerializedName("pict_url")
    private String pictUrl;

    @SerializedName("seller_id")
    private long sellerId;

    @SerializedName("shop_title")
    private String shopTitle;

    @SerializedName("small_images")
    private Map<String, List<String>> smallImages;

    @SerializedName("title")
    private String title;

    @SerializedName("user_type")
    private long userType;

    @SerializedName("volume")
    private long volume;

    @SerializedName("zk_final_price")
    private String zkFinalPrice;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public long getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(long couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public long getCouponRemainCount() {
        return couponRemainCount;
    }

    public void setCouponRemainCount(long couponRemainCount) {
        this.couponRemainCount = couponRemainCount;
    }

    public String getCouponShareUrl() {
        return couponShareUrl;
    }

    public void setCouponShareUrl(String couponShareUrl) {
        this.couponShareUrl = couponShareUrl;
    }

    public String getCouponStartFee() {
        return couponStartFee;
    }

    public void setCouponStartFee(String couponStartFee) {
        this.couponStartFee = couponStartFee;
    }

    public String getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(String couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public long getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(long couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getLevelOneCategoryId() {
        return levelOneCategoryId;
    }

    public void setLevelOneCategoryId(long levelOneCategoryId) {
        this.levelOneCategoryId = levelOneCategoryId;
    }

    public String getLevelOneCategoryName() {
        return levelOneCategoryName;
    }

    public void setLevelOneCategoryName(String levelOneCategoryName) {
        this.levelOneCategoryName = levelOneCategoryName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public Map<String, List<String>> getSmallImages() {
        return smallImages;
    }

    public void setSmallImages(Map<String, List<String>> smallImages) {
        this.smallImages = smallImages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice;
    }

    @Override
    public String toString() {
        return "Content{" +
                "categoryId=" + categoryId +
                ", categoryName=" + categoryName +
                ", clickUrl='" + clickUrl + '\'' +
                ", commissionRate='" + commissionRate + '\'' +
                ", couponAmount=" + couponAmount +
                ", couponClickUrl='" + couponClickUrl + '\'' +
                ", couponEndTime='" + couponEndTime + '\'' +
                ", couponInfo=" + couponInfo +
                ", couponRemainCount=" + couponRemainCount +
                ", couponShareUrl='" + couponShareUrl + '\'' +
                ", couponStartFee='" + couponStartFee + '\'' +
                ", couponStartTime='" + couponStartTime + '\'' +
                ", couponTotalCount=" + couponTotalCount +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemId=" + itemId +
                ", levelOneCategoryId=" + levelOneCategoryId +
                ", levelOneCategoryName='" + levelOneCategoryName + '\'' +
                ", nick='" + nick + '\'' +
                ", pictUrl='" + pictUrl + '\'' +
                ", sellerId=" + sellerId +
                ", shopTitle='" + shopTitle + '\'' +
                ", smallImages=" + smallImages +
                ", title='" + title + '\'' +
                ", userType=" + userType +
                ", volume=" + volume +
                ", zkFinalPrice='" + zkFinalPrice + '\'' +
                '}';
    }
}
