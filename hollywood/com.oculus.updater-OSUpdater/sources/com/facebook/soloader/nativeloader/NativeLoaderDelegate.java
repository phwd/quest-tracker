package com.facebook.soloader.nativeloader;

import java.io.IOException;

public interface NativeLoaderDelegate {
    String getLibraryPath(String str) throws IOException;

    int getSoSourcesVersion();

    boolean loadLibrary(String str, int i);
}
