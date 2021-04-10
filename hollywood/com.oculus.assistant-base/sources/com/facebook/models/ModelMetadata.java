package com.facebook.models;

import X.KJ;
import com.facebook.jni.HybridData;

public class ModelMetadata {
    public static final Class TAG = ModelMetadata.class;
    public final HybridData mHybridData;

    private native ModelAssetMetadata getAssetMetadataNative(String str);

    private native String getAssetNative(String str);

    private native String getPropertyNative(String str);

    public native long getVersion();

    static {
        KJ.A05("models-core", 0);
    }

    public ModelMetadata(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public String getAsset(String str) {
        String assetNative = getAssetNative(str);
        if (assetNative.isEmpty()) {
            return null;
        }
        return assetNative;
    }
}
