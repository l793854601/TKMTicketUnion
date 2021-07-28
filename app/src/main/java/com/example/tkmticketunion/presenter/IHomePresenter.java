package com.example.tkmticketunion.presenter;

/**
 * 首页Presenter层接口
 */
public interface IHomePresenter {
    /**
     * 获取首页分类
     */
    void getCategories();

    /**
     * presenter绑定view
     * @param callback
     */
    void registerCallback(IHomeCallback callback);

    /**
     * presenter解绑view
     */
    void unregisterCallback();
}
