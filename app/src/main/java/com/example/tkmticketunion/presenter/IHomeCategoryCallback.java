package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.model.domain.Banner;
import com.example.tkmticketunion.model.domain.Content;

import java.util.List;

public interface IHomeCategoryCallback {

    void onBannersLoaded(List<Banner> banners);

    /**
     * 加载中
     * @param isRefresh
     */
    void onLoading(boolean isRefresh);

    /**
     * 数据加载成功
     * @param contents
     * @param isRefresh
     */
    void onContentsLoaded(List<Content> contents, boolean isRefresh);

    /**
     * 数据加载错误
     * @param isRefresh
     */
    void onError(boolean isRefresh);

    /**
     * 数据加载为空
     * @param isRefresh
     */
    void onEmpty(boolean isRefresh);
}
