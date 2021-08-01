package com.example.tkmticketunion.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tkmticketunion.R;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.utils.URLUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Content> mList = new ArrayList<>();

    public HomeCategoryAdapter(Context context, List<Content> list) {
        mContext = context;
        mList = list;
    }

    public void setContents(List<Content> contents, boolean isRefresh) {
        if (isRefresh) {
            mList.clear();
        }
        mList.addAll(contents);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(contentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Content content = mList.get(position);
        holder.bind(content);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        ImageView mIvCover;

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.tv_off)
        TextView mTvOff;

        @BindView(R.id.tv_price)
        TextView mTvPrice;

        @BindView(R.id.tv_origin_price)
        TextView mTvOriginPrice;

        @BindView(R.id.tv_buy_count)
        TextView mTvBuyCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //  原价设置中划线
            //  Paint.ANTI_ALIAS_FLAG：抗锯齿
            //  Paint.STRIKE_THRU_TEXT_FLAG：中划线
            mTvOriginPrice.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void bind(Content content) {
            //  封面
            Resources resources = mContext.getResources();

            Glide.with(mContext)
                    .load(URLUtil.getImageUrl(content.getPictUrl()))
                    .placeholder(resources.getDrawable(R.drawable.ic_launcher_foreground))
                    .error(resources.getDrawable(R.drawable.ic_launcher_background))
                    .into(mIvCover);
            //  标题
            mTvTitle.setText(content.getTitle());
            //  节XX元
            mTvOff.setText(resources.getString(R.string.content_off_price_format, content.getCouponAmount()));
            //  价格
            mTvPrice.setText(resources.getString(R.string.content_price_format, Double.parseDouble(content.getZkFinalPrice()) - content.getCouponAmount()));
            //  原价
            mTvOriginPrice.setText(resources.getString(R.string.content_origin_price_format, Double.parseDouble(content.getZkFinalPrice())));
            //  XX人购买
            mTvBuyCount.setText(resources.getString(R.string.content_buy_count_format, content.getVolume()));
        }
    }
}
