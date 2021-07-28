package com.example.tkmticketunion.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.utils.Constants;
import com.example.tkmticketunion.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";

    protected enum LoadDataState {
        NONE, LOADING, SUCCESS, EMPTY, FAILED
    }

    private Unbinder mUnbinder;

    private LoadDataState mLoadDataState;

    private FrameLayout mFlContainer;
    private View mContentView;
    private View mLoadingView;
    private View mEmptyView;
    private View mNetworkErrorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container, savedInstanceState);
        mFlContainer = rootView.findViewById(R.id.fl_container);
        loadStatesView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
        initViews(view);
        initPresenter();
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        release();
    }

    protected void setupState(LoadDataState state) {
        mLoadDataState = state;

        mContentView.setVisibility((state == LoadDataState.SUCCESS ? View.VISIBLE : View.GONE));
        mLoadingView.setVisibility((state == LoadDataState.LOADING ? View.VISIBLE : View.GONE));
        mEmptyView.setVisibility((state == LoadDataState.EMPTY ? View.VISIBLE : View.GONE));
        mNetworkErrorView.setVisibility((state == LoadDataState.FAILED ? View.VISIBLE : View.GONE));
    }

    protected void setupErrorState(String message) {
        setupState(LoadDataState.FAILED);
        //   TODO: 将message显示在textview中
    }

    /**
     * 创建RootView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_container, container, false);
    }

    /**
     * 创建ContentView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getContentViewLayoutId();
        return inflater.inflate(layoutId, container, false);
    }

    /**
     * 创建lLoadingView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadLoadingView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_loading, container, false);
    }

    /**
     * 创建EmptyView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadEmptyView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_empty, container, false);
    }

    /**
     * 创建NetworkErrorView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected View loadNetworkErrorView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_network_error, container, false);
    }

    /**
     * 返回contentView的布局id
     * @return
     */
    protected abstract int getContentViewLayoutId();

    /**
     * 创建Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 初始化控件
     * @param rootView
     */
    protected void initViews(View rootView) {

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

    /**
     * 加载各种状态的view
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    private void loadStatesView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  显示数据的View
        mContentView = loadContentView(inflater, container, savedInstanceState);
        mFlContainer.addView(mContentView);
        //  LoadingView
        mLoadingView = loadLoadingView(inflater, container, savedInstanceState);
        mFlContainer.addView(mLoadingView);
        //  EmptyView
        mEmptyView = loadEmptyView(inflater, container, savedInstanceState);
        mFlContainer.addView(mEmptyView);
        //  NetworkErrorView
        mNetworkErrorView = loadNetworkErrorView(inflater, container, savedInstanceState);
        mFlContainer.addView(mNetworkErrorView);

        //  将全部view隐藏
        setupState(LoadDataState.NONE);
    }

}
