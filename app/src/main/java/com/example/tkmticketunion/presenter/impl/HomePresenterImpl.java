package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.Category;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.presenter.IHomeCallback;
import com.example.tkmticketunion.presenter.IHomePresenter;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.RetrofitUtil;

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
        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        Call<HttpResponse<List<Category>>> call = api.getHomeCategories();
        call.enqueue(new Callback<HttpResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<Category>>> call, Response<HttpResponse<List<Category>>> response) {
                HttpResponse<List<Category>> body = response.body();
                if (body.isSuccess() && body.getData() != null) {
                    LogUtil.d(TAG, "load home categories: " + body);
                    if (mCallback != null) {
                        mCallback.onGetCategoriesSuccess(body.getData());
                    }
                } else {
                    //  TODO: 请求失败，回调
                }

            }

            @Override
            public void onFailure(Call<HttpResponse<List<Category>>> call, Throwable t) {
                LogUtil.d(TAG, "load home categories failed: " + t);
                //  TODO: 请求失败，回调
            }
        });
    }

    @Override
    public void registerCallback(IHomeCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterCallback() {
        mCallback = null;
    }
}
