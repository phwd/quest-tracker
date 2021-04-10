package com.facebook.soloader;

import com.facebook.soloader.nativeloader.NativeLoaderDelegate;

public class NativeLoaderToSoLoaderDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public boolean loadLibrary(String shortName, int flags) {
        return SoLoader.loadLibrary(shortName, 0 | ((flags & 1) != 0 ? 16 : 0));
    }
}
