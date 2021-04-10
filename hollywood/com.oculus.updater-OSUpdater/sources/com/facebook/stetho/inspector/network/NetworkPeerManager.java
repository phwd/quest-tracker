package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import javax.annotation.Nullable;

public class NetworkPeerManager extends ChromePeerManager {
    private static NetworkPeerManager sInstance;
    private AsyncPrettyPrinterRegistry mAsyncPrettyPrinterRegistry;
    private final ResponseBodyFileManager mResponseBodyFileManager;

    @Nullable
    public static synchronized NetworkPeerManager getInstanceOrNull() {
        NetworkPeerManager networkPeerManager;
        synchronized (NetworkPeerManager.class) {
            networkPeerManager = sInstance;
        }
        return networkPeerManager;
    }

    public ResponseBodyFileManager getResponseBodyFileManager() {
        return this.mResponseBodyFileManager;
    }

    @Nullable
    public AsyncPrettyPrinterRegistry getAsyncPrettyPrinterRegistry() {
        return this.mAsyncPrettyPrinterRegistry;
    }
}
