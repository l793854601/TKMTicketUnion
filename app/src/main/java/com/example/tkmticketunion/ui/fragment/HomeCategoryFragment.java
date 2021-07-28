package com.example.tkmticketunion.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.Category;

import butterknife.BindView;

public class HomeCategoryFragment extends BaseFragment {

    private Category mCategory;

    @BindView(R.id.tv)
    TextView mTv;

    public static HomeCategoryFragment newInstance(Category category) {
        HomeCategoryFragment fragment = new HomeCategoryFragment();
        fragment.mCategory = category;
        return fragment;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_home_category;
    }

    @Override
    protected void initViews(View rootView) {
        setupState(LoadDataState.SUCCESS);
        mTv.setText(mCategory.getTitle());
    }
}
