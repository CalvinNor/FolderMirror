package com.calvinnor.foldermirror.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.calvinnor.foldermirror.R;
import com.calvinnor.foldermirror.fragment.BaseFragment;

/**
 * Base Activity to inherit from.
 * All common code and abstraction layer goes in here.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final int NO_LAYOUT = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        setupToolbar();

        // Setup the root fragment only on first launch
        if (savedInstanceState == null) return;
        setupFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuLayout = getMenuLayout();
        if (menuLayout == NO_LAYOUT) return false;

        getMenuInflater().inflate(menuLayout, menu);
        return true;
    }

    /**
     * Override this method to provide an activity layout.
     *
     * @return The layout resource ID.
     */
    @LayoutRes
    protected int getContentLayout() {
        return NO_LAYOUT;
    }

    /**
     * Override this method to provide a root fragment.
     *
     * @return The BaseFragment instance to inflate.
     */
    @Nullable
    protected BaseFragment getFragment() {
        return null;
    }

    /**
     * Override this method to provide the fragment container ID.
     *
     * @return An IdRes representing the container to place the root fragment.
     */
    @IdRes
    protected int getFragmentContainer() {
        return NO_LAYOUT;
    }

    /**
     * Override this method to provide a Menu layout.
     *
     * @return The Menu resource ID.
     */
    @MenuRes
    protected int getMenuLayout() {
        return NO_LAYOUT;
    }

    /**
     * Replace the fragment present in provided container.
     *
     * @param containerId The container to replace.
     * @param fragment    The fragment to place.
     */
    protected void replaceFragment(@IdRes int containerId,
                                   @NonNull BaseFragment fragment,
                                   boolean addToBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment, fragment.getFragmentTag());
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setupLayout() {
        int activityLayout = getContentLayout();
        if (activityLayout == NO_LAYOUT) return;

        setContentView(activityLayout);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFragment() {
        int fragmentContainer = getFragmentContainer();
        if (fragmentContainer == NO_LAYOUT) return;

        BaseFragment fragment = getFragment();
        if (fragment == null) return;

        replaceFragment(fragmentContainer, fragment, false);
    }
}
