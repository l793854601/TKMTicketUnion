package com.example.tkmticketunion.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.ChoiceCategory;
import com.example.tkmticketunion.model.domain.ChoiceContents;
import com.example.tkmticketunion.presenter.IChoiceCallback;
import com.example.tkmticketunion.presenter.IChoicePresenter;
import com.example.tkmticketunion.presenter.impl.ChoicePresenterImpl;
import com.example.tkmticketunion.ui.adapter.ChoiceCategoryAdapter;
import com.example.tkmticketunion.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChoiceFragment extends BaseFragment implements IChoiceCallback {

    private static final String TAG = "ChoiceFragment";

    private IChoicePresenter mPresenter;

    private List<ChoiceCategory> mCategories = new ArrayList<>();

    private ChoiceCategoryAdapter mCategoryAdapter;

    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;

    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    @Override
    protected void initPresenter() {
        mPresenter = new ChoicePresenterImpl();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_chioce;
    }

    @Override
    protected void initViews(View rootView) {
        mCategoryAdapter = new ChoiceCategoryAdapter(getContext(), mCategories);
        mRvCategory.setAdapter(mCategoryAdapter);
        mRvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvCategory.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void loadData() {
        if (mPresenter != null) {
            mPresenter.getCategories();
        }
    }

    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterViewCallback();
        }
    }

    @Override
    public void onLoading() {
        setupState(LoadDataState.LOADING);
    }

    @Override
    public void onEmpty() {
        setupState(LoadDataState.EMPTY);
    }

    @Override
    public void onError() {
        setupState(LoadDataState.FAILED);
    }

    @Override
    public void onCategoriesLoaded(List<ChoiceCategory> categories) {
        setupState(LoadDataState.SUCCESS);
        LogUtil.d(TAG, "onCategoriesLoaded: " + categories);

        //  刷新UI
        mCategories.clear();
        mCategories.addAll(categories);
        mCategoryAdapter.notifyDataSetChanged(categories.size() > 0);

        //  获取分类下的内容
        if (categories.size() > 0) {
            ChoiceCategory category = categories.get(0);
            getContentByCategory(category);
        }
    }


    @Override
    public void onContentsLoaded(ChoiceContents contents) {
        LogUtil.d(TAG, "onContentsLoaded: " + contents);
    }

    @Override
    public void onGetContentsLoading() {

    }

    @Override
    public void onGetContentsEmpty() {

    }

    @Override
    public void onGetContentsError() {

    }

    private void getContentByCategory(ChoiceCategory category) {
        if (mPresenter != null && category != null) {
            mPresenter.getContentsByCategory(category);
        }
    }
}
