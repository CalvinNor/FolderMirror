package com.calvinnor.foldermirror.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.calvinnor.foldermirror.R;

public class BaseActivity extends AppCompatActivity {

    private static final int NO_LAYOUT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        setupToolbar();
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
     * Override this method to provide a Menu layout.
     *
     * @return The Menu resource ID.
     */
    @MenuRes
    protected int getMenuLayout() {
        return NO_LAYOUT;
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
}
