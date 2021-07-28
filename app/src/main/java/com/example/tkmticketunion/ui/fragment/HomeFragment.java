package com.example.tkmticketunion.ui.fragment;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.presenter.IHomeCallback;
import com.example.tkmticketunion.presenter.IHomePresenter;
import com.example.tkmticketunion.presenter.impl.HomePresenterImpl;

import java.util.List;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mPresenter;

    @Override
    protected int getRootViewLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 创建Presenter
     */
    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenterImpl();
        mPresenter.registerCallback(this);
    }

    /**
     * 网络请求，首先加载分类
     */
    @Override
    protected void loadData() {
        mPresenter.getCategories();
    }

    /**
     * 加载分类成功
     * @param categories
     */
    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {

    }

    @Override
    protected void release() {

        if (mPresenter != null) {
            mPresenter.unregisterCallback();
        }
    }
}
