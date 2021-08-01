package com.example.tkmticketunion.ui.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tkmticketunion.R;
import com.example.tkmticketunion.model.domain.Content;
import com.example.tkmticketunion.utils.URLUtil;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<Content, HomeBannerAdapter.ViewHolder> {

    public HomeBannerAdapter(List<Content> datas) {
        super(datas);
    }

    @Override
    public ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_banner, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindView(ViewHolder holder, Content data, int position, int size) {
        holder.bindHolder(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvBanner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvBanner = itemView.findViewById(R.id.iv_banner);
        }

        public void bindHolder(Content content) {
            Glide.with(mIvBanner.getContext())
                    .load(URLUtil.getImageUrl(content.getPictUrl()))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(mIvBanner);
        }
    }
}
