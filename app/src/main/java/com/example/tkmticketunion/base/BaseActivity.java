package com.example.tkmticketunion.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        setContentView(getContentViewLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initPresenter();
        initViews();
        initFragments();
        initListeners();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        release();
    }

    protected void initData(Bundle savedInstanceState) {

    }

    protected void initPresenter() {

    }

    protected void initViews() {

    }

    protected void initFragments() {

    }

    protected void initListeners() {

    }

    protected void loadData() {

    }

    protected void release() {

    }

    abstract protected int getContentViewLayoutId();
}
