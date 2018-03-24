package com.calvinnor.foldermirror.model;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Data class for storing a Directory structure.
 */
public class Directory {

    private Uri rootUri;

    private final String dirName;
    private final int dirSize;
    private final long dirModified;

    public Directory(@NonNull Uri uri, @NonNull String dirName, int dirSize, long dirModified) {
        this.rootUri = uri;
        this.dirName = dirName;
        this.dirSize = dirSize;
        this.dirModified = dirModified;
    }

    public Uri getRootUri() {
        return rootUri;
    }

    public String getDirName() {
        return dirName;
    }

    public int getDirSize() {
        return dirSize;
    }

    public long getDirModified() {
        return dirModified;
    }

}
