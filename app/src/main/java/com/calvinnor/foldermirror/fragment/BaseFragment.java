package com.calvinnor.foldermirror.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base Fragment to inherit from.
 * All common code and abstraction goes here.
 */
public abstract class BaseFragment extends Fragment {

    private static final int NO_LAYOUT = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return getInflatedView(inflater);
    }

    /**
     * Override this method to provide a fragment layout.
     *
     * @return The layout resource ID.
     */
    @LayoutRes
    protected int getLayout() {
        return NO_LAYOUT;
    }

    /**
     * Override this method to provide a fragment tag.
     * This will be used in Fragment Transactions.
     *
     * @return A string representing the fragment tag.
     */
    @NonNull
    public abstract String getFragmentTag();

    @Nullable
    private View getInflatedView(@NonNull LayoutInflater inflater) {
        int activityLayout = getLayout();
        if (activityLayout == NO_LAYOUT) return null;

        return inflater.inflate(getLayout(), null, false);
    }
}
