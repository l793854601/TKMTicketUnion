package com.example.tkmticketunion.ui.fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseFragment;
import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.presenter.IHomeCategoryCallback;
import com.example.tkmticketunion.presenter.IHomeCategoryPresenter;
import com.example.tkmticketunion.presenter.impl.HomeCategoryPresenterImpl;
import com.example.tkmticketunion.ui.adapter.HomeCategoryAdapter;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.ToastUtil;
import com.example.tkmticketunion.utils.UIUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeCategoryFragment extends BaseFragment implements IHomeCategoryCallback {

    private static final String TAG = "HomeCategoryFragment";

    private Category mCategory;

    private List<Content> mBanners = new ArrayList<>();

    private List<Content> mContents = new ArrayList<>();

    private IHomeCategoryPresenter mPresenter;

    private HomeCategoryAdapter mAdapter;

    @BindView(R.id.refresh_layout)
    TwinklingRefreshLayout mRefreshLayout;

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
        //  设置下拉刷新、上拉加载更多
        mRefreshLayout.setHeaderView(new SinaRefreshView(getContext()));
        mRefreshLayout.setBottomView(new LoadingView(getContext()));

        //  初始先禁用下拉刷新、上拉加载更多，防止重复加载
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadmore(false);

        //  设置RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                //  此处传值单位为px，如果想写成dp，则需要转换
                outRect.bottom = UIUtil.px2dip(getContext(), 5);
            }
        };
        mRv.addItemDecoration(itemDecoration);

        //  设置RecyclerView的Adapter
        mAdapter = new HomeCategoryAdapter(getContext(), mCategory.getTitle(), mBanners, mContents);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {
        //  下拉刷新、上拉加载更多监听
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                LogUtil.d(TAG, "onRefresh");
                mPresenter.getContentByCategoryId(mCategory.getId(), true);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                LogUtil.d(TAG, "onRefresh");
                mPresenter.getContentByCategoryId(mCategory.getId(), false);
            }
        });

        mAdapter.setOnItemClickListener(new HomeCategoryAdapter.OnItemClickListener() {
            @Override
            public void onBannerClicked(Content content, int position) {
                LogUtil.d(TAG, "onBannerClicked: position = " + position + ", title = " + content.getTitle());
            }

            @Override
            public void onContentClicked(Content content, int position) {
                LogUtil.d(TAG, "onContentClicked: position = " + position + ", title = " + content.getTitle());
            }
        });
    }

    @Override
    protected void loadData() {
        setupState(LoadDataState.LOADING);
        mPresenter.getContentByCategoryId(mCategory.getId(), true);
    }

    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterViewCallback();
        }
    }

    @Override
    public void onBannersLoaded(List<Content> banners) {
        mBanners.clear();
        mBanners.addAll(banners);
        mAdapter.setBanners(banners);
    }

    @Override
    public void onLoading(boolean isRefresh) {
        LogUtil.d(TAG, "onLoading: " + isRefresh);
    }

    @Override
    public void onContentsLoaded(List<Content> contents, boolean isRefresh) {
        setupState(LoadDataState.SUCCESS);
        mAdapter.setContents(contents, isRefresh);

        if (isRefresh) {
            mRefreshLayout.finishRefreshing();
        } else {
            mRefreshLayout.finishLoadmore();
            Resources resources = getContext().getResources();
            ToastUtil.showToast(getContext(), resources.getString(R.string.load_more_tip_format, contents.size()), Toast.LENGTH_SHORT);
        }

        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadmore(contents.size() > 0);
    }

    @Override
    public void onError(boolean isRefresh) {
        LogUtil.d(TAG, "onError: " + isRefresh);

        if (isRefresh) {
            setupState(LoadDataState.FAILED);
            mRefreshLayout.finishRefreshing();
        } else {
            mRefreshLayout.finishLoadmore();
        }

        Resources resources = getContext().getResources();
        ToastUtil.showToast(getContext(), resources.getString(R.string.tip_network_error), Toast.LENGTH_SHORT);
    }

    @Override
    public void onEmpty(boolean isRefresh) {
        LogUtil.d(TAG, "onEmpty: " + isRefresh);

        if (isRefresh) {
            setupState(LoadDataState.EMPTY);
            mRefreshLayout.finishRefreshing();
        } else {
            mRefreshLayout.finishLoadmore();
            mRefreshLayout.setEnableRefresh(false);
            Resources resources = getContext().getResources();
            ToastUtil.showToast(getContext(), resources.getString(R.string.load_more_empty_tip), Toast.LENGTH_SHORT);
        }
    }
}
