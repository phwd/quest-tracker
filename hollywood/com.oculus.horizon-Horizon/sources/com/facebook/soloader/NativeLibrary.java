package com.facebook.soloader;

import java.util.List;
import javax.annotation.Nullable;

public abstract class NativeLibrary {
    public static final String TAG = "com.facebook.soloader.NativeLibrary";
    public boolean mLibrariesLoaded = false;
    @Nullable
    public List<String> mLibraryNames;
    @Nullable
    public volatile UnsatisfiedLinkError mLinkError = null;
    public Boolean mLoadLibraries = true;
    public final Object mLock = new Object();

    public NativeLibrary(List<String> list) {
        this.mLibraryNames = list;
    }
}
