package com.facebook.models;

public class StaticAssetMeta {
    public String mCacheKey;
    public String mModelName;
    public int mModelVersion;
    public String mName;
    public String mURL;

    public StaticAssetMeta(String str, int i, String str2, String str3, String str4) {
        this.mModelName = str;
        this.mModelVersion = i;
        this.mName = str2;
        this.mURL = str3;
        this.mCacheKey = str4;
    }

    public String getCacheKey() {
        return this.mCacheKey;
    }

    public String getModelName() {
        return this.mModelName;
    }

    public int getModelVersion() {
        return this.mModelVersion;
    }

    public String getName() {
        return this.mName;
    }

    public String getURL() {
        return this.mURL;
    }
}
