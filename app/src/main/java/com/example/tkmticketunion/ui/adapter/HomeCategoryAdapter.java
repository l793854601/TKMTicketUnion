package com.example.tkmticketunion.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.model.domain.Content;

import java.util.ArrayList;
import java.util.List;

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

        private ImageView mIvCover;
        private TextView mTvTitle;
        private TextView mTvOff;
        private TextView mTvPrice;
        private TextView mTvOriginPrice;
        private TextView mTvBuyCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mIvCover = itemView.findViewById(R.id.iv_cover);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvOff = itemView.findViewById(R.id.tv_off);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvOriginPrice = itemView.findViewById(R.id.tv_origin_price);
            mTvBuyCount = itemView.findViewById(R.id.tv_buy_count);
        }

        public void bind(Content content) {

        }
    }
}
