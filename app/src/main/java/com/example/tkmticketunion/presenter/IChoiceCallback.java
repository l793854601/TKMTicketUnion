package com.example.tkmticketunion.presenter;

import com.example.tkmticketunion.base.IBaseView;
import com.example.tkmticketunion.model.domain.ChoiceCategory;
import com.example.tkmticketunion.model.domain.ChoiceContents;

import java.util.List;

public interface IChoiceCallback extends IBaseView {

    /**
     * 分类加载成功
     * @param categories
     */
    void onCategoriesLoaded(List<ChoiceCategory> categories);

    /**
     * 分类内容列表加载成功
     * @param contents
     */
    void onContentsLoaded(ChoiceContents contents);

    /**
     * 正在加载分类内容
     */
    void onGetContentsLoading();

    /**
     * 加载分类内容为空
     */
    void onGetContentsEmpty();

    /**
     * 加载分类内容失败
     */
    void onGetContentsError();
}
