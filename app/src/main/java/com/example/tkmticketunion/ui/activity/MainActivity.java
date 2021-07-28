package com.example.tkmticketunion.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tkmticketunion.R;
import com.example.tkmticketunion.ui.fragment.ChoiceFragment;
import com.example.tkmticketunion.ui.fragment.HomeFragment;
import com.example.tkmticketunion.ui.fragment.SaleFragment;
import com.example.tkmticketunion.ui.fragment.SearchFragment;
import com.example.tkmticketunion.utils.LogUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String SELECT_INDEX_KEY = "SELECT_INDEX_KEY";

    private Unbinder mUnbinder;

    //  BottomNavigationView选中的index
    private int mSelectIndex = 0;

    //  Fragments
    private HomeFragment mHomeFragment;
    private SaleFragment mSaleFragment;
    private ChoiceFragment mChoiceFragment;
    private SearchFragment mSearchFragment;

    @BindView(R.id.navigation_view)
    BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        initData(savedInstanceState);
        initFragments();
        initEvents();

        //  移除所有Fragment
        removeAllFragmentsIfNeed();
        //  默认选中首页
        switchToCurrentFragment();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //  Activity意外退出，则保存mSelectIndex
        outState.putInt(SELECT_INDEX_KEY, mSelectIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mSelectIndex = savedInstanceState.getInt(SELECT_INDEX_KEY);
        }
    }

    private void initFragments() {
        //  首页
        mHomeFragment = new HomeFragment();
        //  特惠
        mSaleFragment = new SaleFragment();
        //  精选
        mChoiceFragment = new ChoiceFragment();
        //  搜索
        mSearchFragment = new SearchFragment();
    }

    private void initEvents() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            int index = -1;
            Fragment target = null;

            switch (item.getItemId()) {
                case R.id.item_home:
                    //  首页
                    index = 0;
                    target = mHomeFragment;
                    break;
                case R.id.item_sale:
                    //  特惠
                    index = 1;
                    target = mSaleFragment;
                    break;
                case R.id.item_choice:
                    //  精选
                    index = 2;
                    target = mChoiceFragment;
                    break;
                case R.id.item_search:
                    //  搜索
                    index = 3;
                    target = mSearchFragment;
                    break;
            }

            //  防止重复点击
            if (index != mSelectIndex && target != null) {
                LogUtil.d(TAG, "onSelectItem: " + item.getTitle());
                mSelectIndex = index;
                switchToFragment(target);
            }

            return true;
        });
    }

    /**
     * 根据mSelectIndex，显示当前Fragment
     */
    private void switchToCurrentFragment() {
        Fragment target = null;
        int itemId = -1;

        if (mSelectIndex == 0) {
            //  首页
            target = mHomeFragment;
            itemId = R.id.item_home;
        } else if (mSelectIndex == 1) {
            //  特惠
            target = mSaleFragment;
            itemId = R.id.item_sale;
        } else if (mSelectIndex == 2) {
            //  精选
            target = mChoiceFragment;
            itemId = R.id.item_choice;
        } else if (mSelectIndex == 3) {
            //  搜索
            target = mSearchFragment;
            itemId = R.id.item_search;
        }

        if (target != null && itemId != -1) {
            mNavigationView.setSelectedItemId(itemId);
            switchToFragment(target);
        }
    }

    /**
     * 显示Fragment，不存在则添加，存在则显示
     * @param target
     */
    private void switchToFragment(Fragment target) {
        //  先隐藏所有的Fragment
        hideAllFragments();
        //  获取tag
        String tag = target.getClass().getSimpleName();
        //  添加/隐藏
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            //  不存在，则添加
            transaction.add(R.id.fragment_container, target, tag);
        } else {
            //  已存在，则显示
            transaction.show(target);
        }
        //  提交事务
        transaction.commitNow();
    }

    /**
     * 移除此Activity中的所有Fragment
     */
    private void removeAllFragmentsIfNeed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();

        for (Fragment fragment : fragments) {
            transaction.remove(fragment);
        }

        transaction.commitNow();
    }

    /**
     * 隐藏此Activity中的所有Fragment
     */
    private void hideAllFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();

        for (Fragment fragment : fragments) {
            transaction.hide(fragment);
        }

        transaction.commitNow();
    }
}