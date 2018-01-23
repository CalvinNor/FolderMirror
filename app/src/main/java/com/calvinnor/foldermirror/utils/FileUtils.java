package com.calvinnor.foldermirror.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class for file / folder operations like create, copy, delete
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    private static final int COPY_BUFFER_SIZE = 1024;

    // Prevent instantiation
    private FileUtils() {
        throw new RuntimeException("Utility class. Do not instantiate");
    }

    /**
     * Get a file for the given file path.
     * <p>
     * If exists, the handle to the file will be returned.
     * If the file does not exist, a new file will be created and returned.
     * <p>
     * Returns null on any IOException.
     *
     * @param filePath The path of the file.
     * @return A file handle if exists / created. Null otherwise.
     */
    @Nullable
    public static File getFileHandle(@NonNull String filePath) {
        File file = new File(filePath);

        // If the file exists, no need to create it.
        if (file.exists()) return file;

        // Create a new file and return it.
        return createNewFile(file);
    }

    /**
     * Creates a file, given the file handler.
     * DO NOT CALL THIS METHOD WITHOUT ENSURING THAT THE FILE DOES NOT EXIST.
     *
     * @param toCreate The file to create from.
     * @return The file handler if no errors, else null.
     */
    @Nullable
    public static File createNewFile(@NonNull File toCreate) {
        if (toCreate.exists()) throw new RuntimeException("File already exists!");

        boolean fileCreated = false;
        try {
            fileCreated = toCreate.createNewFile();
        } catch (IOException e) {
            Log.e(TAG, "Could not create file. Reason: " + e.getMessage());
        }

        return fileCreated ? toCreate : null;
    }

    /**
     * Delete the file, given the handler to it.
     *
     * @param toDelete The file to delete.
     * @return A boolean stating if the file was successfully deleted.
     */
    public static boolean deleteFile(@NonNull File toDelete) {
        return toDelete.delete();
    }

    /**
     * Copy file contents from source to destination.
     *
     * @param srcFile  The source file.
     * @param destFile The destination file.
     */
    public static void copyFile(@NonNull File srcFile, @NonNull File destFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[COPY_BUFFER_SIZE];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found. Reason: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException while reading. Reason: " + e.getMessage());
        }

        if (inputStream != null) closeStream(inputStream);
        if (outputStream != null) closeStream(outputStream);
    }

    private static void closeStream(@NonNull Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.e(TAG, "Unexpected error while closing stream: " + e.getMessage());
        }
    }
}
