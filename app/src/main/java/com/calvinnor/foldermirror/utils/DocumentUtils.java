package com.calvinnor.foldermirror.utils;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.calvinnor.foldermirror.model.Directory;

import java.io.File;

/**
 * Utility class to make use of {@link android.provider.DocumentsProvider} utility
 * to access and write to files.
 */
public class DocumentUtils {

    private static final String[] DOCUMENT_PROJECTION = new String[]{
            DocumentsContract.Document.COLUMN_DISPLAY_NAME,
            DocumentsContract.Document.COLUMN_SIZE,
            DocumentsContract.Document.COLUMN_LAST_MODIFIED
    };

    private static final int DISPLAY_NAME_POS = 0;
    private static final int DISPLAY_SIZE_POS = 1;
    private static final int DISPLAY_LAST_MODIFIED_POS = 2;

    /**
     * Get an intent to pick directory using DocumentProvider
     *
     * @return An intent to use with startActivityForResult.
     */
    @NonNull
    public static Intent getDocumentSelectionIntent() {
        return new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
    }

    /**
     * Get a Uri for the root Document.
     *
     * @param treeUri The Uri obtained from DocumentProvider.
     * @return A Uri which can be used with a ContentResolver.
     */
    @NonNull
    public static Uri getDocumentUri(@NonNull Uri treeUri) {
        return DocumentsContract.buildDocumentUriUsingTree(treeUri,
                DocumentsContract.getTreeDocumentId(treeUri));
    }

    /**
     * Get a Uri for the child Documents.
     *
     * @param treeUri The Uri obtained from DocumentProvider.
     * @return A Uri which can be used with a ContentResolver.
     */
    @NonNull
    public static Uri getChildDocumentsUri(@NonNull Uri treeUri) {
        return DocumentsContract.buildChildDocumentsUriUsingTree(treeUri,
                DocumentsContract.getTreeDocumentId(treeUri));
    }

    public static String[] getProjection() {
        return DOCUMENT_PROJECTION;
    }

    @Nullable
    public static Directory getDirectoryFromUri(@NonNull ContentResolver contentResolver,
                                                @NonNull Uri documentUri) {

        Cursor contentCursor = contentResolver.query(
                documentUri,
                DocumentUtils.getProjection(),
                null,
                null,
                null);

        if (contentCursor == null || !contentCursor.moveToFirst()) {
            return null;
        }

        Directory directory = new Directory(
                documentUri,
                contentCursor.getString(DISPLAY_NAME_POS),
                contentCursor.getInt(DISPLAY_SIZE_POS),
                contentCursor.getLong(DISPLAY_LAST_MODIFIED_POS));

        // Free up the cursor before return
        contentCursor.close();

        return directory;
    }
}
