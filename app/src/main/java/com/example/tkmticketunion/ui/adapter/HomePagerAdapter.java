package com.example.tkmticketunion.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tkmticketunion.model.domain.HomeCategory;
import com.example.tkmticketunion.ui.fragment.HomeCategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private List<HomeCategory> mList = new ArrayList<>();

    private List<Fragment> mFragments = new ArrayList<>();

    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        HomeCategory category = mList.get(position);
        return HomeCategoryFragment.newInstance(category);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        HomeCategory category = mList.get(position);
        return category.getTitle();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void setCategories(List<HomeCategory> categories) {
        mList.clear();
        mList.addAll(categories);
        notifyDataSetChanged();
    }
}
