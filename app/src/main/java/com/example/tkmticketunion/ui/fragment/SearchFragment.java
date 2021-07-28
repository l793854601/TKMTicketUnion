package com.example.tkmticketunion.ui.fragment;

import android.view.View;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initViews(View rootView) {
        setupState(LoadDataState.SUCCESS);
    }
}
