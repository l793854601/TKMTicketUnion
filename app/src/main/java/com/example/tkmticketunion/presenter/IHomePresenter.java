package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBasePresenter;

/**
 * 首页Presenter层接口
 */
public interface IHomePresenter extends IBasePresenter<IHomeCallback> {
    /**
     * 获取首页分类
     */
    void getCategories();
}
