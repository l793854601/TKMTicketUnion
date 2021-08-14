package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.ChoiceCategory;
import com.example.tkmticketunion.model.domain.ChoiceContents;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.presenter.IChoiceCallback;
import com.example.tkmticketunion.presenter.IChoicePresenter;
import com.example.tkmticketunion.utils.RetrofitUtil;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChoicePresenterImpl implements IChoicePresenter {

    private IChoiceCallback mCallback;

    @Override
    public void registerViewCallback(IChoiceCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        if (mCallback != null) {
            mCallback = null;
        }
    }

    @Override
    public void getCategories() {
        if (mCallback != null) {
            mCallback.onLoading();
        }

        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        api.getChoiceCategories().enqueue(new Callback<HttpResponse<List<ChoiceCategory>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<ChoiceCategory>>> call, Response<HttpResponse<List<ChoiceCategory>>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    HttpResponse<List<ChoiceCategory>> body = response.body();
                    if (body != null) {
                        List<ChoiceCategory> categories = body.getData();
                        if (categories != null && categories.size() > 0) {
                            if (mCallback != null) {
                                mCallback.onCategoriesLoaded(categories);
                            }
                        } else {
                            if (mCallback != null) {
                                mCallback.onEmpty();
                            }
                        }
                    } else {
                        if (mCallback != null) {
                            mCallback.onError();
                        }
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<ChoiceCategory>>> call, Throwable t) {
                if (mCallback != null) {
                    mCallback.onError();
                }
            }
        });
    }

    @Override
    public void getContentsByCategory(ChoiceCategory category) {
        if (mCallback != null) {
            mCallback.onGetContentsLoading();
        }

        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        api.getChoiceCategoryContents(category.getFavoritesId() + "").enqueue(new Callback<HttpResponse<ChoiceContents>>() {
            @Override
            public void onResponse(Call<HttpResponse<ChoiceContents>> call, Response<HttpResponse<ChoiceContents>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    HttpResponse<ChoiceContents> body = response.body();
                    if (body != null) {
                        ChoiceContents contents = body.getData();
                        if (contents != null &&
                            contents.getTbkDgOptimusMaterialResponse() != null &&
                            contents.getTbkDgOptimusMaterialResponse().getResultList() != null &&
                            contents.getTbkDgOptimusMaterialResponse().getResultList().getMapData() != null &&
                            contents.getTbkDgOptimusMaterialResponse().getResultList().getMapData().size() > 0) {
                            if (mCallback != null) {
                                mCallback.onContentsLoaded(contents);
                            }
                        } else {
                            if (mCallback != null) {
                                mCallback.onGetContentsEmpty();
                            }
                        }
                    } else {
                        if (mCallback != null) {
                            mCallback.onGetContentsError();
                        }
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onGetContentsError();
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<ChoiceContents>> call, Throwable t) {
                if (mCallback != null) {
                    mCallback.onGetContentsError();
                }
            }
        });

    }
}
