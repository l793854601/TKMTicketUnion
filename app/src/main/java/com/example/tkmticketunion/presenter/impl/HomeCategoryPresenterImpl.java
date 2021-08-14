package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.HomeContent;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.presenter.IHomeCategoryCallback;
import com.example.tkmticketunion.presenter.IHomeCategoryPresenter;
import com.example.tkmticketunion.utils.Constants;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.RetrofitUtil;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeCategoryPresenterImpl implements IHomeCategoryPresenter {

    private static final String TAG = "HomeCategoryPresenterImpl";

    private IHomeCategoryCallback mCallback;
    private int mPage = Constants.START_PAGE;

    @Override
    public void getContentByCategoryId(int categoryId, boolean isRefresh) {
        if (mCallback != null) {
            mCallback.onLoading(isRefresh);
        }

        if (isRefresh) {
            mPage = Constants.START_PAGE;
        } else {
            mPage++;
        }

        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<HttpResponse<List<HomeContent>>> call = api.getHomeCategoryContents(categoryId, mPage);
        call.enqueue(new Callback<HttpResponse<List<HomeContent>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<HomeContent>>> call, Response<HttpResponse<List<HomeContent>>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    HttpResponse<List<HomeContent>> body = response.body();
                    if (body != null) {
                        List<HomeContent> contents = body.getData();
                        LogUtil.d(TAG, "onContentsLoaded: " + contents);
                        if (contents == null || contents.size() == 0) {
                            if (mCallback != null) {
                                mCallback.onEmpty(isRefresh);
                            }
                        } else {
                            if (mCallback != null) {
                                mCallback.onContentsLoaded(contents, isRefresh);
                            }

                            if (isRefresh) {
                                int fromIndex = contents.size() - 5;
                                int toIndex = contents.size();
                                List<HomeContent> banners = contents.subList(fromIndex, toIndex);
                                if (mCallback != null) {
                                    mCallback.onBannersLoaded(banners);
                                }
                            }
                        }
                    } else {
                        setupFailedPage();
                        if (mCallback != null) {
                            mCallback.onError(isRefresh);
                        }
                    }
                } else {
                    setupFailedPage();
                    if (mCallback != null) {
                        mCallback.onError(isRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<HomeContent>>> call, Throwable t) {
                setupFailedPage();
                if (mCallback != null) {
                    mCallback.onError(isRefresh);
                }
            }
        });

    }

    @Override
    public void registerViewCallback(IHomeCategoryCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        if (mCallback != null) {
            mCallback = null;
        }
    }

    /**
     * 请求失败，则将当前页-1
     */
    private void setupFailedPage() {
        mPage--;
        if (mPage < Constants.START_PAGE) {
            mPage = Constants.START_PAGE;
        }
    }
}
