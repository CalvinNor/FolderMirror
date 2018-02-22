package com.calvinnor.foldermirror.fragment;

import android.support.annotation.NonNull;

import com.calvinnor.foldermirror.R;

/**
 * Fragment to house the Home functionality
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    @NonNull
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @NonNull
    @Override
    public String getFragmentTag() {
        return TAG;
    }
}
