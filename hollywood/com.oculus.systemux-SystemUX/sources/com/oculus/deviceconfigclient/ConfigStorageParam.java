package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageParam {
    private final boolean mIsSessionless;
    @Nullable
    private final String mLoggingId;
    private final String mName;
    private final String mType;
    private final String mValue;

    public ConfigStorageParam(String str, String str2, String str3, @Nullable String str4, boolean z) {
        this.mName = str;
        this.mType = str2;
        this.mValue = str3;
        this.mLoggingId = str4;
        this.mIsSessionless = z;
    }

    public String getName() {
        return this.mName;
    }

    public String getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    @Nullable
    public String getLoggingId() {
        return this.mLoggingId;
    }

    public boolean isSessionless() {
        return this.mIsSessionless;
    }
}
