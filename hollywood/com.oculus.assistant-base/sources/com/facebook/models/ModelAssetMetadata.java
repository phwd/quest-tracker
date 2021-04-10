package com.facebook.models;

public class ModelAssetMetadata {
    public final boolean fromCache;
    public final String path;

    public ModelAssetMetadata(String str, boolean z) {
        this.fromCache = z;
        this.path = str;
    }
}
