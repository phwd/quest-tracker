package com.facebook.soloader.nativeloader;

public interface NativeLoaderDelegate {
    int getSoSourcesVersion();

    boolean loadLibrary(String str, int i);
}
