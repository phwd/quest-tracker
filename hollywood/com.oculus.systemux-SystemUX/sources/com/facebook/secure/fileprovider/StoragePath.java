package com.facebook.secure.fileprovider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.HashMap;
import javax.annotation.Nullable;

public enum StoragePath {
    ROOT_PATH("root-path", false),
    FILES_PATH("files-path", true),
    CACHE_PATH("cache-path", true),
    EXTERNAL_PATH("external-path", false),
    EXTERNAL_FILES_PATH("external-files-path", false),
    EXTERNAL_CACHE_PATH("external-cache-path", false);
    
    private static final File DEVICE_ROOT = new File("/");
    private static final HashMap<String, StoragePath> sPathMap = new HashMap<>();
    private final boolean mIsPrivate;
    private final String mTagName;

    static {
        StoragePath[] values = values();
        for (StoragePath storagePath : values) {
            sPathMap.put(storagePath.tagName(), storagePath);
        }
    }

    private StoragePath(String str, boolean z) {
        this.mTagName = str;
        this.mIsPrivate = z;
    }

    public String tagName() {
        return this.mTagName;
    }

    public boolean isPrivate() {
        return this.mIsPrivate;
    }

    @Nullable
    @SuppressLint({"BadMethodUse-android.content.Context.getExternalCacheDir"})
    public File getDirectoryForContext(Context context) {
        switch (this) {
            case ROOT_PATH:
                return DEVICE_ROOT;
            case FILES_PATH:
                return context.getFilesDir();
            case CACHE_PATH:
                return context.getCacheDir();
            case EXTERNAL_PATH:
                return Environment.getExternalStorageDirectory();
            case EXTERNAL_FILES_PATH:
                return context.getExternalFilesDir(null);
            case EXTERNAL_CACHE_PATH:
                return context.getExternalCacheDir();
            default:
                return null;
        }
    }

    @Nullable
    public static StoragePath getPathForTagName(String str) {
        return sPathMap.get(str);
    }
}
