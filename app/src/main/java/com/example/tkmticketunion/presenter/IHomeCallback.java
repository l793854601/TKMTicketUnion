package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.model.domain.Category;

import java.util.List;

/**
 * 首页View层接口
 */
public interface IHomeCallback {

    /**
     * 正在加载首页分类
     */
    void onLoadingCategories();

    /**
     * 获取首页分类成功
     * @param categories
     */
    void onGetCategoriesSuccess(List<Category> categories);

    /**
     * 获取首页分类为空
     */
    void onGetCategoriesEmpty();

    /**
     * 获取首页分类失败
     */
    void onGetCategoriesError();
}
