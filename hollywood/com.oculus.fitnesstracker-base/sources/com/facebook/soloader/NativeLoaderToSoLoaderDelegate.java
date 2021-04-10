package com.facebook.soloader;

import com.facebook.soloader.nativeloader.NativeLoaderDelegate;

public final class NativeLoaderToSoLoaderDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public final boolean loadLibrary(String str, int i) {
        return SoLoader.loadLibrary(str, ((i & 1) != 0 ? 16 : 0) | 0);
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public final int getSoSourcesVersion() {
        return SoLoader.getSoSourcesVersion();
    }
}
