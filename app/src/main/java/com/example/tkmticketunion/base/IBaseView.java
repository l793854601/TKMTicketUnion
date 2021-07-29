package com.example.tkmticketunion.base;

public interface IBaseView {
    /**
     * 正在加载数据
     */
    void onLoading();

    /**
     * 加载数据为空
     */
    void onEmpty();

    /**
     * 加载数据失败
     */
    void onError();
}
