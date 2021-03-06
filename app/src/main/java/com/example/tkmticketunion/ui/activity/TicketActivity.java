package com.example.tkmticketunion.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tkmticketunion.R;
import com.example.tkmticketunion.base.BaseActivity;
import com.example.tkmticketunion.model.domain.HomeContent;
import com.example.tkmticketunion.model.domain.Ticket;
import com.example.tkmticketunion.presenter.ITicketCallback;
import com.example.tkmticketunion.presenter.ITicketPresenter;
import com.example.tkmticketunion.presenter.impl.TicketPresenterImpl;
import com.example.tkmticketunion.utils.Constants;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.ToastUtil;
import com.example.tkmticketunion.utils.URLUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TicketActivity extends BaseActivity implements ITicketCallback {

    private static final String TAG = "TicketActivity";

    private static final String CONTENT_KEY = "CONTENT_KEY";

    private ITicketPresenter mPresenter;

    private HomeContent mContent;

    private String mCode;

    private boolean mIsInstallTaobao = false;

    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;

    @BindView(R.id.ll_content)
    LinearLayout mLlContent;

    @BindView(R.id.iv_cover)
    ImageView mIvCover;

    @BindView(R.id.tv_code)
    TextView mTvCode;

    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;

    public static void startActivity(Context context, HomeContent content) {
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
        mContent = (HomeContent) getIntent().getSerializableExtra(CONTENT_KEY);

        //  ????????????????????????
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(Constants.TAOBAO_PACKAGE_NAME, 0);
            if (packageInfo != null) {
                LogUtil.d(TAG, "taobao installed");
                mIsInstallTaobao = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TicketPresenterImpl();
        mPresenter.registerViewCallback(this);
    }

    @Override
    protected void initViews() {
        //  ????????????
        Glide.with(this)
                .load(URLUtil.getFullUrl(mContent.getPictUrl()))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(mIvCover);

        //  ???????????????????????????????????????????????????
        if (mIsInstallTaobao) {
            mTvGetCode.setText(R.string.open_taobao_get_code);
        } else {
            mTvGetCode.setText(R.string.copy_code);
        }
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
        mLlLoading.setVisibility(View.VISIBLE);
        mLlContent.setVisibility(View.GONE);
    }

    @Override
    public void onEmpty() {
        LogUtil.d(TAG, "onEmpty");
        mLlLoading.setVisibility(View.GONE);
        mLlContent.setVisibility(View.GONE);
        String msg = getResources().getString(R.string.tip_network_error);
        ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void onError() {
        LogUtil.d(TAG, "onError");
        mLlLoading.setVisibility(View.GONE);
        mLlContent.setVisibility(View.GONE);
    }

    @Override
    public void onGetTicketSuccess(Ticket ticket) {
        LogUtil.d(TAG, "onGetTicketSuccess: ticket = " + ticket);
        mLlLoading.setVisibility(View.GONE);
        mLlContent.setVisibility(View.VISIBLE);
        refreshUI(ticket);
    }

    private void refreshUI(Ticket ticket) {
        //  ?????????
        mCode = ticket.getTicketCode();
        //  ????????????
        if (TextUtils.isEmpty(mCode)) {
            String msg = getResources().getString(R.string.tip_ticket_code_empty);
            ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
            return;
        }
        //  ?????????????????????????????????
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

        //  ???????????????????????????
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String label = "sob_taobao_ticket_code";
        ClipData clipData = ClipData.newPlainText(label, mCode);
        clipboardManager.setPrimaryClip(clipData);

        if (mIsInstallTaobao) {
            //  ???????????????????????????????????????
            try {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName(
                        Constants.TAOBAO_PACKAGE_NAME,
                        Constants.TAOBAO_MAIN_CLASS_NAME);
                intent.setComponent(componentName);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //  ???????????????????????????Toast??????
            String msg = getResources().getString(R.string.copy_success);
            ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
        }
    }
}