package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.presenter.IHomeCategoryCallback;
import com.example.tkmticketunion.presenter.IHomeCategoryPresenter;
import com.example.tkmticketunion.utils.Constants;
import com.example.tkmticketunion.utils.RetrofitUtil;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeCategoryPresenterImpl implements IHomeCategoryPresenter {

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
        Call<HttpResponse<List<Content>>> call = api.getCategoryContents(categoryId, mPage);
        call.enqueue(new Callback<HttpResponse<List<Content>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Content>>> call, Response<HttpResponse<List<Content>>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    HttpResponse<List<Content>> body = response.body();
                    if (body != null) {
                        List<Content> contents = body.getData();
                        if (contents == null || contents.size() == 0) {
                            if (mCallback != null) {
                                mCallback.onEmpty(isRefresh);
                            }
                        } else {
                            if (mCallback != null) {
                                mCallback.onContentsLoaded(contents, isRefresh);
                            }
                        }
                    } else {
                        if (mCallback != null) {
                            mCallback.onError(isRefresh);
                        }
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onError(isRefresh);
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<Content>>> call, Throwable t) {
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
}
