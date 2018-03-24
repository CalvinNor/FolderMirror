package com.calvinnor.foldermirror.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.calvinnor.foldermirror.R;
import com.calvinnor.foldermirror.model.Directory;
import com.calvinnor.foldermirror.utils.DocumentUtils;

import butterknife.BindView;

/**
 * Fragment to house the Home functionality
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    private static final int REQUEST_CODE = 101;

    @BindView(R.id.home_pick_directory) Button directoryPick;
    @BindView(R.id.home_directory_display) TextView displayDirectory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        directoryPick.setOnClickListener(v -> startActivityForResult(DocumentUtils.getDocumentSelectionIntent(), REQUEST_CODE));
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri rootUri = DocumentUtils.getDocumentUri(data.getData());

            Directory directory = DocumentUtils.getDirectoryFromUri(getActivity().getContentResolver(),
                    rootUri);

            displayDirectory.setText(directory.getDirName());
        }
    }
}
