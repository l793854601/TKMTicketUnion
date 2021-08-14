package com.example.tkmticketunion.model.domain;

import com.example.tkmticketunion.base.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChoiceContents extends BaseModel {

    @SerializedName("tbk_dg_optimus_material_response")
    private TbkDgOptimusMaterialResponseDTO tbkDgOptimusMaterialResponse;

    public TbkDgOptimusMaterialResponseDTO getTbkDgOptimusMaterialResponse() {
        return tbkDgOptimusMaterialResponse;
    }

    public void setTbkDgOptimusMaterialResponse(TbkDgOptimusMaterialResponseDTO tbkDgOptimusMaterialResponse) {
        this.tbkDgOptimusMaterialResponse = tbkDgOptimusMaterialResponse;
    }

    public static class TbkDgOptimusMaterialResponseDTO {
        @SerializedName("is_default")
        private String isDefault;
        @SerializedName("result_list")
        private ResultListDTO resultList;
        @SerializedName("total_count")
        private Integer totalCount;
        @SerializedName("request_id")
        private String requestId;

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public ResultListDTO getResultList() {
            return resultList;
        }

        public void setResultList(ResultListDTO resultList) {
            this.resultList = resultList;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public static class ResultListDTO {
            @SerializedName("map_data")
            private List<MapDataDTO> mapData;

            public List<MapDataDTO> getMapData() {
                return mapData;
            }

            public void setMapData(List<MapDataDTO> mapData) {
                this.mapData = mapData;
            }

            public static class MapDataDTO {
                @SerializedName("category_id")
                private Integer categoryId;
                @SerializedName("click_url")
                private String clickUrl;
                @SerializedName("commission_rate")
                private String commissionRate;
                @SerializedName("coupon_amount")
                private Integer couponAmount;
                @SerializedName("coupon_click_url")
                private Object couponClickUrl;
                @SerializedName("coupon_end_time")
                private Object couponEndTime;
                @SerializedName("coupon_info")
                private String couponInfo;
                @SerializedName("coupon_remain_count")
                private Integer couponRemainCount;
                @SerializedName("coupon_share_url")
                private Object couponShareUrl;
                @SerializedName("coupon_start_fee")
                private String couponStartFee;
                @SerializedName("coupon_start_time")
                private Object couponStartTime;
                @SerializedName("coupon_total_count")
                private Integer couponTotalCount;
                @SerializedName("item_id")
                private Long itemId;
                @SerializedName("level_one_category_id")
                private Integer levelOneCategoryId;
                @SerializedName("nick")
                private Object nick;
                @SerializedName("pict_url")
                private String pictUrl;
                @SerializedName("reserve_price")
                private String reservePrice;
                @SerializedName("seller_id")
                private Long sellerId;
                @SerializedName("shop_title")
                private Object shopTitle;
                @SerializedName("small_images")
                private Object smallImages;
                @SerializedName("title")
                private String title;
                @SerializedName("user_type")
                private Integer userType;
                @SerializedName("volume")
                private Integer volume;
                @SerializedName("white_image")
                private Object whiteImage;
                @SerializedName("zk_final_price")
                private Object zkFinalPrice;

                public Integer getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(Integer categoryId) {
                    this.categoryId = categoryId;
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

                public Integer getCouponAmount() {
                    return couponAmount;
                }

                public void setCouponAmount(Integer couponAmount) {
                    this.couponAmount = couponAmount;
                }

                public Object getCouponClickUrl() {
                    return couponClickUrl;
                }

                public void setCouponClickUrl(Object couponClickUrl) {
                    this.couponClickUrl = couponClickUrl;
                }

                public Object getCouponEndTime() {
                    return couponEndTime;
                }

                public void setCouponEndTime(Object couponEndTime) {
                    this.couponEndTime = couponEndTime;
                }

                public String getCouponInfo() {
                    return couponInfo;
                }

                public void setCouponInfo(String couponInfo) {
                    this.couponInfo = couponInfo;
                }

                public Integer getCouponRemainCount() {
                    return couponRemainCount;
                }

                public void setCouponRemainCount(Integer couponRemainCount) {
                    this.couponRemainCount = couponRemainCount;
                }

                public Object getCouponShareUrl() {
                    return couponShareUrl;
                }

                public void setCouponShareUrl(Object couponShareUrl) {
                    this.couponShareUrl = couponShareUrl;
                }

                public String getCouponStartFee() {
                    return couponStartFee;
                }

                public void setCouponStartFee(String couponStartFee) {
                    this.couponStartFee = couponStartFee;
                }

                public Object getCouponStartTime() {
                    return couponStartTime;
                }

                public void setCouponStartTime(Object couponStartTime) {
                    this.couponStartTime = couponStartTime;
                }

                public Integer getCouponTotalCount() {
                    return couponTotalCount;
                }

                public void setCouponTotalCount(Integer couponTotalCount) {
                    this.couponTotalCount = couponTotalCount;
                }

                public Long getItemId() {
                    return itemId;
                }

                public void setItemId(Long itemId) {
                    this.itemId = itemId;
                }

                public Integer getLevelOneCategoryId() {
                    return levelOneCategoryId;
                }

                public void setLevelOneCategoryId(Integer levelOneCategoryId) {
                    this.levelOneCategoryId = levelOneCategoryId;
                }

                public Object getNick() {
                    return nick;
                }

                public void setNick(Object nick) {
                    this.nick = nick;
                }

                public String getPictUrl() {
                    return pictUrl;
                }

                public void setPictUrl(String pictUrl) {
                    this.pictUrl = pictUrl;
                }

                public String getReservePrice() {
                    return reservePrice;
                }

                public void setReservePrice(String reservePrice) {
                    this.reservePrice = reservePrice;
                }

                public Long getSellerId() {
                    return sellerId;
                }

                public void setSellerId(Long sellerId) {
                    this.sellerId = sellerId;
                }

                public Object getShopTitle() {
                    return shopTitle;
                }

                public void setShopTitle(Object shopTitle) {
                    this.shopTitle = shopTitle;
                }

                public Object getSmallImages() {
                    return smallImages;
                }

                public void setSmallImages(Object smallImages) {
                    this.smallImages = smallImages;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Integer getUserType() {
                    return userType;
                }

                public void setUserType(Integer userType) {
                    this.userType = userType;
                }

                public Integer getVolume() {
                    return volume;
                }

                public void setVolume(Integer volume) {
                    this.volume = volume;
                }

                public Object getWhiteImage() {
                    return whiteImage;
                }

                public void setWhiteImage(Object whiteImage) {
                    this.whiteImage = whiteImage;
                }

                public Object getZkFinalPrice() {
                    return zkFinalPrice;
                }

                public void setZkFinalPrice(Object zkFinalPrice) {
                    this.zkFinalPrice = zkFinalPrice;
                }
            }
        }
    }
}
