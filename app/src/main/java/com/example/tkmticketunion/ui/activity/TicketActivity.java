package com.example.tkmticketunion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseActivity;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.model.domain.Ticket;
import com.example.tkmticketunion.presenter.ITicketCallback;
import com.example.tkmticketunion.presenter.ITicketPresenter;
import com.example.tkmticketunion.presenter.impl.TicketPresenterImpl;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.ToastUtil;
import com.example.tkmticketunion.utils.UIUtil;
import com.example.tkmticketunion.utils.URLUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TicketActivity extends BaseActivity implements ITicketCallback {

    private static final String TAG = "TicketActivity";

    private static final String CONTENT_KEY = "CONTENT_KEY";

    private ITicketPresenter mPresenter;

    private Content mContent;

    private String mCode;

    @BindView(R.id.iv_cover)
    ImageView mIvCover;

    @BindView(R.id.tv_code)
    TextView mTvCode;

    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;

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
    protected void initData(Bundle savedInstanceState) {
        mContent = (Content) getIntent().getSerializableExtra(CONTENT_KEY);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TicketPresenterImpl();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected void initViews() {
        //  加载封面
        Glide.with(this)
                .load(URLUtil.getFullUrl(mContent.getPictUrl()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(mIvCover);
        //  简单处理，隐藏可操作的控件
        mTvCode.setVisibility(View.INVISIBLE);
        mTvGetCode.setVisibility(View.INVISIBLE);
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
        String msg = getResources().getString(R.string.tip_network_error);
        ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void onError() {
        LogUtil.d(TAG, "onError");
    }

    @Override
    public void onGetTicketSuccess(Ticket ticket) {
        LogUtil.d(TAG, "onGetTicketSuccess: ticket = " + ticket);
        refreshUI(ticket);
    }
//2.0￥7yKSXlea9re￥ https://m.tb.cn/h.4A8WROA  小苏打香水洗衣液持久留香味持久整箱批家用内衣机洗专用实惠袋装
    private void refreshUI(Ticket ticket) {
        //  淘口令
        mCode = ticket.getTicketCode();
        //  容错处理
        if (TextUtils.isEmpty(mCode)) {
            String msg = getResources().getString(R.string.tip_ticket_code_empty);
            ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
            return;
        }
        //  将控件显示，并刷新数据
        mTvCode.setVisibility(View.VISIBLE);
        mTvGetCode.setVisibility(View.VISIBLE);
        mTvCode.setText(mCode);
    }

    @OnClick(R.id.iv_back)
    void onBackClicked() {
        finish();
    }

    @OnClick(R.id.tv_get_code)
    void onGetCodeClicked() {
        LogUtil.d(TAG, "onGetCodeClicked");
        if (TextUtils.isEmpty(mCode)) {
            String msg = getResources().getString(R.string.tip_ticket_code_empty);
            ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
            return;
        }

        //  TODO: 如果安装了淘宝，则跳转淘宝
    }
}