package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.model.domain.Category;

import java.util.List;

/**
 * 首页View层接口
 */
public interface IHomeCallback {

    /**
     * 获取首页分类成功
     * @param categories
     */
    void onGetCategoriesSuccess(List<Category> categories);

}
