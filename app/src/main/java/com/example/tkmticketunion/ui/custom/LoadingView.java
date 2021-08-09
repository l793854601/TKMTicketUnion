package com.example.tkmticketunion.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.utils.LogUtil;

public class LoadingView extends AppCompatImageView {

    private static final String TAG = "LoadingView";

    private RotateAnimation rotateAnimation;

    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setImageResource(R.mipmap.loading);

        rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        LogUtil.d(TAG, "visibility = " + visibility);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d(TAG, "onAttachedToWindow");
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d(TAG, "onDetachedFromWindow");
        stopAnimation();
    }

    private void startAnimation() {
        if (rotateAnimation != null) {
            startAnimation(rotateAnimation);
        }
    }

    private void stopAnimation() {
        if (rotateAnimation != null) {
            rotateAnimation.cancel();
        }
    }
}
