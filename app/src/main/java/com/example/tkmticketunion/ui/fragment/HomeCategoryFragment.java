package com.example.tkmticketunion.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.Banner;
import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.presenter.IHomeCategoryCallback;
import com.example.tkmticketunion.presenter.IHomeCategoryPresenter;
import com.example.tkmticketunion.presenter.impl.HomeCategoryPresenterImpl;
import com.example.tkmticketunion.ui.adapter.HomeCategoryAdapter;
import com.example.tkmticketunion.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeCategoryFragment extends BaseFragment implements IHomeCategoryCallback {

    private static final String TAG = "HomeCategoryFragment";

    private Category mCategory;

    private List<Content> mList = new ArrayList<>();

    private IHomeCategoryPresenter mPresenter;

    private HomeCategoryAdapter mAdapter;

    @BindView(R.id.rv)
    RecyclerView mRv;

    /**
     * 静态构造方法
     * @param category
     * @return
     */
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
    protected void initPresenter() {
        mPresenter = new HomeCategoryPresenterImpl();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected void initViews(View rootView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                //  此处传值单位为px，如果想写成dp，则需要转换（转换工具类）
                outRect.top = 5;
                outRect.bottom = 5;
            }
        };
        mRv.addItemDecoration(itemDecoration);
        mAdapter = new HomeCategoryAdapter(getContext(), mList);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mPresenter.getContentByCategoryId(mCategory.getId(), true);
    }

    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterViewCallback();
        }
    }

    @Override
    public void onBannersLoaded(List<Banner> banners) {

    }

    @Override
    public void onLoading(boolean isRefresh) {
        LogUtil.d(TAG, "onLoading: " + isRefresh);

        if (isRefresh) {
            setupState(LoadDataState.LOADING);
        }
    }

    @Override
    public void onContentsLoaded(List<Content> contents, boolean isRefresh) {
        LogUtil.d(TAG, "onContentsLoaded: " + contents);

        if (isRefresh) {
            setupState(LoadDataState.SUCCESS);
        }

        mAdapter.setContents(contents, isRefresh);
    }

    @Override
    public void onError(boolean isRefresh) {
        LogUtil.d(TAG, "onError: " + isRefresh);

        if (isRefresh) {
            setupState(LoadDataState.FAILED);
        }

    }

    @Override
    public void onEmpty(boolean isRefresh) {
        LogUtil.d(TAG, "onEmpty: " + isRefresh);

        if (isRefresh) {
            setupState(LoadDataState.EMPTY);
        }
    }
}
