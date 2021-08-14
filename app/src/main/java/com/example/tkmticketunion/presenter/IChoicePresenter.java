package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBasePresenter;
import com.example.tkmticketunion.model.domain.ChoiceCategory;

public interface IChoicePresenter extends IBasePresenter<IChoiceCallback> {

    /**
     * 获取分类
     */
    void getCategories();

    /**
     * 根据分类加载内容
     * @param category
     */
    void getContentsByCategory(ChoiceCategory category);

    /**
     * 根据分类，重新加载内容
     * @param category
     */
    default void reloadContentsByCategory(ChoiceCategory category) {
        getContentsByCategory(category);
    }
}
