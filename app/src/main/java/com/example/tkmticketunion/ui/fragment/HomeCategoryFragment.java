package com.example.tkmticketunion.ui.fragment;

import android.view.View;
import android.widget.TextView;

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
    protected int getRootViewLayoutId() {
        return R.layout.fragment_home_category;
    }

    @Override
    protected void initViews(View rootView) {
        super.initViews(rootView);

        mTv.setText(mCategory.getTitle());
    }
}
