package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBasePresenter;

public interface IHomeCategoryPresenter extends IBasePresenter<IHomeCategoryCallback> {
    /**
     * 根据分类Id、分页，获取内容
     * @param categoryId
     * @param isRefresh
     */
    void getContentByCategoryId(int categoryId, boolean isRefresh);
}
