package com.example.tkmticketunion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseActivity;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.model.domain.Ticket;
import com.example.tkmticketunion.presenter.ITicketCallback;
import com.example.tkmticketunion.presenter.ITicketPresenter;
import com.example.tkmticketunion.presenter.impl.TicketPresenterImpl;
import com.example.tkmticketunion.utils.LogUtil;

public class TicketActivity extends BaseActivity implements ITicketCallback {

    private static final String TAG = "TicketActivity";

    private static final String CONTENT_KEY = "CONTENT_KEY";

    private ITicketPresenter mPresenter;

    private Content mContent;

    public static void startActivity(Context context, Content content) {
        Intent intent = new Intent(context, TicketActivity.class);
        intent.putExtra(CONTENT_KEY, content);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TicketPresenterImpl();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mContent = (Content) getIntent().getSerializableExtra(CONTENT_KEY);
    }

    @Override
    protected void loadData() {
        mPresenter.getTicket(mContent.getTitle(), mContent.getClickUrl());
    }

    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterViewCallback();
        }
    }

    @Override
    public void onLoading() {
        LogUtil.d(TAG, "onLoading");
    }

    @Override
    public void onEmpty() {
        LogUtil.d(TAG, "onLoading");
    }

    @Override
    public void onError() {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onGetTicketSuccess(Ticket ticket) {
        LogUtil.d(TAG, "onGetTicketSuccess: ticket = " + ticket);
    }
}