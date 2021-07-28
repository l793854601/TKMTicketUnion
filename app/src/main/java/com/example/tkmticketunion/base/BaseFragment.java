package com.example.tkmticketunion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return loadRootView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        release();
    }

    /**
     * 创建rootView，子类无需重写
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getRootViewLayoutId();
        return inflater.inflate(layoutId, container, false);
    }

    /**
     * 返回rootView的布局id
     * @return
     */
    protected abstract int getRootViewLayoutId();

    /**
     * 创建Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 加载数据
     */
    protected void loadData() {

    }

    /**
     * 释放资源
     */
    protected void release() {

    }
}
