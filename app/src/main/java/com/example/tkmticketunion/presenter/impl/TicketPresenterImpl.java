package com.example.tkmticketunion.presenter.impl;

import com.example.tkmticketunion.model.api.API;
import com.example.tkmticketunion.model.domain.GetTicketParam;
import com.example.tkmticketunion.model.domain.HttpResponse;
import com.example.tkmticketunion.model.domain.Ticket;
import com.example.tkmticketunion.presenter.ITicketCallback;
import com.example.tkmticketunion.presenter.ITicketPresenter;
import com.example.tkmticketunion.utils.RetrofitUtil;
import com.example.tkmticketunion.utils.URLUtil;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketPresenterImpl implements ITicketPresenter {

    private ITicketCallback mCallback;

    @Override
    public void registerViewCallback(ITicketCallback callback) {
        mCallback = callback;
    }

    @Override
    public void unregisterViewCallback() {
        if (mCallback != null) {
            mCallback = null;
        }
    }

    @Override
    public void getTicket(String title, String url) {
        if (mCallback != null) {
            mCallback.onLoading();
        }

        Retrofit retrofit = RetrofitUtil.getInstance().getRetrofit();
        API api = retrofit.create(API.class);
        GetTicketParam param = new GetTicketParam(title, URLUtil.getFullUrl(url));
        api.getTicket(param).enqueue(new Callback<HttpResponse<Ticket>>() {
            @Override
            public void onResponse(Call<HttpResponse<Ticket>> call, Response<HttpResponse<Ticket>> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    HttpResponse<Ticket> body = response.body();
                    if (body.isSuccess() && body.getData() != null) {
                        Ticket ticket = body.getData();
                        if (mCallback != null) {
                            mCallback.onGetTicketSuccess(ticket);
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
            public void onFailure(Call<HttpResponse<Ticket>> call, Throwable t) {
                if (mCallback != null) {
                    mCallback.onError();
                }
            }
        });
    }
}
