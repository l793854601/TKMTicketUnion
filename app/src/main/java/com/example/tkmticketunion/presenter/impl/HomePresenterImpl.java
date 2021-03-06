package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.HomeCategory;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.presenter.IHomeCallback;
import com.example.tkmticketunion.presenter.IHomePresenter;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.RetrofitUtil;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresenter {

    private static final String TAG = "HomePresenterImpl";

    private IHomeCallback mCallback;

    @Override
    public void getCategories() {
        //  显示loading
        if (mCallback != null) {
            mCallback.onLoading();
        }
        //  网络请求
        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<HttpResponse<List<HomeCategory>>> call = api.getHomeCategories();
        call.enqueue(new Callback<HttpResponse<List<HomeCategory>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<HomeCategory>>> call, Response<HttpResponse<List<HomeCategory>>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    //  Http请求成功
                    HttpResponse<List<HomeCategory>> body = response.body();
                    if (body != null) {
                        if (body.isSuccess()) {
                            List<HomeCategory> categories = body.getData();
                            if (categories != null && categories.size() > 0) {
                                //  请求成功
                                if (mCallback != null) {
                                    mCallback.onGetCategoriesSuccess(categories);
                                }
                            } else {
                                //  请求分类为空
                                if (mCallback != null) {
                                    mCallback.onEmpty();
                                }
                            }
                        } else {
                            //  Http业务错误
                            if (mCallback != null) {
                                mCallback.onError();
                            }
                        }
                    } else {
                        //  Http请求结果为null
                        if (mCallback != null) {
                            mCallback.onError();
                        }
                    }
                } else {
                    //  Http请求失败
                    if (mCallback != null) {
                        mCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<HomeCategory>>> call, Throwable t) {
                LogUtil.d(TAG, "load home categories failed: " + t);
                if (mCallback != null) {
                    mCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerViewCallback(IHomeCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        mCallback = null;
    }
}