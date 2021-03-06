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
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tkmticketunion.R;
import com.example.tkmticketunion.model.domain.HomeContent;
import com.example.tkmticketunion.utils.LogUtil;
import com.example.tkmticketunion.utils.URLUtil;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onBannerClicked(HomeContent content, int position);
        void onContentClicked(HomeContent content, int position);
    }

    private static final String TAG = "HomeCategoryAdapter";

    private static final int ITEM_TYPE_BANNER = 1;
    private static final int ITEM_TYPE_TITLE = 2;
    private static final int ITEM_TYPE_CONTENT = 3;

    private OnItemClickListener mOnItemClickListener;

    private Context mContext;
    private List<HomeContent> mBanners = new ArrayList<>();
    private List<HomeContent> mContents = new ArrayList<>();
    private String mTitle;

    public HomeCategoryAdapter(Context context, String title, List<HomeContent> banners, List<HomeContent> contents) {
        mContext = context;
        mTitle = title;
        mBanners = banners;
        mContents = contents;
    }

    public void setBanners(List<HomeContent> banners) {
        mBanners.clear();
        mBanners.addAll(banners);
        notifyDataSetChanged();
    }

    public void setContents(List<HomeContent> contents, boolean isRefresh) {
        if (isRefresh) {
            mContents.clear();
        }
        mContents.addAll(contents);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            //  ?????????
            return ITEM_TYPE_BANNER;
        } else if (position == 1) {
            //  ??????
            return ITEM_TYPE_TITLE;
        }
        //  ??????
        return ITEM_TYPE_CONTENT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.d(TAG, "onCreateViewHolder: ");
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (viewType == ITEM_TYPE_BANNER) {
            //  ?????????
            View contentView = layoutInflater.inflate(R.layout.item_home_category_banner, parent, false);
            BannerViewHolder viewHolder = new BannerViewHolder(contentView);
            return viewHolder;
        } else if (viewType == ITEM_TYPE_TITLE) {
            //  ??????
            View contentView = layoutInflater.inflate(R.layout.item_home_category_title, parent, false);
            TitleViewHolder viewHolder = new TitleViewHolder(contentView);
            return viewHolder;
        } else if (viewType == ITEM_TYPE_CONTENT) {
            //  ??????
            View contentView = layoutInflater.inflate(R.layout.item_home_category_content, parent, false);
            ContentViewHolder viewHolder = new ContentViewHolder(contentView);
            contentView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition() - 2;
                    HomeContent content = mContents.get(position);
                    mOnItemClickListener.onBannerClicked(content, position);
                }
            });
            return viewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            //  ?????????
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.bindHolder(mBanners);
        } else if (holder instanceof TitleViewHolder) {
            //  ??????
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            titleViewHolder.bindHolder(mTitle);
        } else if (holder instanceof ContentViewHolder) {
            //  ??????
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            HomeContent content = mContents.get(position - 2);
            contentViewHolder.bind(content);
        }
    }

    @Override
    public int getItemCount() {
        return mContents.size() + 2;
    }

    /**
     * ?????????
     */
    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private Banner<HomeContent, HomeBannerAdapter> mBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.banner);
        }

        public void bindHolder(List<HomeContent> contents) {
            mBanner.setAdapter(new HomeBannerAdapter(contents))
                    .addBannerLifecycleObserver((LifecycleOwner) mContext)
                    .setIndicator(new CircleIndicator(mContext));

            //  ????????????Adapter??????????????????????????????
            mBanner.setOnBannerListener((data, position) -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onBannerClicked(data, position);
                }
            });
        }
    }

    /**
     * ??????
     */
    public class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void bindHolder(String title) {
            mTvTitle.setText(title);
        }
    }

    /**
     * ??????
     */
    public class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvCover;
        private TextView mTvTitle;
        private TextView mTvOff;
        private TextView mTvPrice;
        private TextView mTvOriginPrice;
        private TextView mTvBuyCount;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvCover = itemView.findViewById(R.id.iv_cover);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvOff = itemView.findViewById(R.id.tv_off);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvOriginPrice = itemView.findViewById(R.id.tv_origin_price);
            mTvBuyCount = itemView.findViewById(R.id.tv_buy_count);
            //  ?????????????????????
            //  Paint.ANTI_ALIAS_FLAG????????????
            //  Paint.STRIKE_THRU_TEXT_FLAG????????????
            mTvOriginPrice.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void bind(HomeContent content) {
            Resources resources = mContext.getResources();
            //  ??????
            Glide.with(mContext)
                    .load(URLUtil.getHomeCategoryImageUrl(content.getPictUrl()))
                    .placeholder(resources.getDrawable(R.drawable.ic_launcher_foreground))
                    .error(resources.getDrawable(R.drawable.ic_launcher_background))
                    .into(mIvCover);
            //  ??????
            mTvTitle.setText(content.getTitle());
            //  ???XX???
            mTvOff.setText(resources.getString(R.string.content_off_price_format, content.getCouponAmount()));
            //  ??????
            mTvPrice.setText(resources.getString(R.string.content_price_format, Double.parseDouble(content.getZkFinalPrice()) - content.getCouponAmount()));
            //  ??????
            mTvOriginPrice.setText(resources.getString(R.string.content_origin_price_format, Double.parseDouble(content.getZkFinalPrice())));
            //  XX?????????
            mTvBuyCount.setText(resources.getString(R.string.content_buy_count_format, content.getVolume()));
        }
    }
}
