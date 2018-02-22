package com.calvinnor.foldermirror.activity;

import android.support.annotation.Nullable;

import com.calvinnor.foldermirror.R;
import com.calvinnor.foldermirror.fragment.BaseFragment;
import com.calvinnor.foldermirror.fragment.HomeFragment;

/**
 * Activity to house the Home functionality.
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected int getFragmentContainer() {
        return R.id.home_fragment_container;
    }

    @Nullable
    @Override
    protected BaseFragment getFragment() {
        return HomeFragment.newInstance();
    }
}
