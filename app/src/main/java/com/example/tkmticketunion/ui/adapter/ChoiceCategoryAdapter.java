package com.example.tkmticketunion.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.model.domain.ChoiceCategory;

import java.util.List;

public class ChoiceCategoryAdapter extends RecyclerView.Adapter<ChoiceCategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<ChoiceCategory> mList;
    private int mSelectedIndex = -1;

    public ChoiceCategoryAdapter(Context context, List<ChoiceCategory> list) {
        mContext = context;
        mList = list;
    }

    public void notifyDataSetChanged(boolean selectedFirst) {
        if (selectedFirst && mList.size() > 0) {
            mSelectedIndex = 0;
        }
        notifyDataSetChanged();
    }

    public void setSelectedIndex(int selectedIndex) {
        mSelectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_choice_category, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChoiceCategory category = mList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv);
        }

        public void bind(ChoiceCategory category) {
            int position = getAdapterPosition();
            Resources resources = mContext.getResources();
            if (position == mSelectedIndex) {
                mTv.setBackgroundColor(resources.getColor(R.color.choice_catefory_selected));
            } else {
                mTv.setBackgroundColor(resources.getColor(R.color.choice_catefory_normal));
            }
            mTv.setText(category.getFavoritesTitle());
        }
    }
}
