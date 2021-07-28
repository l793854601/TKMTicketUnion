package com.example.tkmticketunion.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.presenter.IHomeCallback;
import com.example.tkmticketunion.presenter.IHomePresenter;
import com.example.tkmticketunion.presenter.impl.HomePresenterImpl;
import com.example.tkmticketunion.ui.adapter.HomePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mPresenter;

    private HomePagerAdapter mPagerAdapter;

    @BindView(R.id.home_indicator)
    TabLayout mHomeIndicator;

    @BindView(R.id.home_pager)
    ViewPager mViewPager;

    /**
     * 加载根布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    protected View loadRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /**
     * ContentView布局
     * @return
     */
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.layout_home_content;
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
     * 初始化控件
     * @param rootView
     */
    @Override
    protected void initViews(View rootView) {
        //  初始化HomePagerAdapter
        mPagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        //  绑定TabLayout与ViewPager
        mHomeIndicator.setupWithViewPager(mViewPager);
    }

    /**
     * 网络请求，首先加载分类
     */
    @Override
    protected void loadData() {
        mPresenter.getCategories();
    }

    /**
     * 正在加载分类
     */
    @Override
    public void onLoadingCategories() {
        setupState(LoadDataState.LOADING);
    }

    /**
     * 加载分类成功
     * @param categories
     */
    @Override
    public void onGetCategoriesSuccess(List<Category> categories) {
        setupState(LoadDataState.SUCCESS);
        mPagerAdapter.setCategories(categories);
    }

    /**
     * 加载分类为空
     */
    @Override
    public void onGetCategoriesEmpty() {
        setupState(LoadDataState.EMPTY);
    }

    /**
     * 加载分类失败
     */
    @Override
    public void onGetCategoriesError() {
        setupState(LoadDataState.FAILED);
    }

    /**
     * 释放资源
     */
    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterCallback();
        }
    }
}
