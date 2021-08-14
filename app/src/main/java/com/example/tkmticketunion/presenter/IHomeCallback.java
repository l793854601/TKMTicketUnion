package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBaseView;
import com.example.tkmticketunion.model.domain.HomeCategory;

import java.util.List;

/**
 * 首页View层接口
 */
public interface IHomeCallback extends IBaseView {

    /**
     * 获取首页分类成功
     * @param categories
     */
    void onGetCategoriesSuccess(List<HomeCategory> categories);
}
