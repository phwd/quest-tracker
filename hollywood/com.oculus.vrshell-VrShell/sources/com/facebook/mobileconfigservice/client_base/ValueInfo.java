package com.facebook.mobileconfigservice.client_base;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ValueInfo {
    final boolean mIsSessionless;
    @Nullable
    final String mLoggingId;
    final String mQueryString;
    final MobileConfigValueSource mSource;
    final int mType;
    @Nullable
    final Object mValue;

    public ValueInfo(int i, boolean z, @Nullable Object obj, @Nullable String str, String str2) {
        this(i, z, obj, str, str2, MobileConfigValueSource.UNKNOWN);
    }

    public ValueInfo(int i, boolean z, @Nullable Object obj, @Nullable String str, String str2, MobileConfigValueSource mobileConfigValueSource) {
        this.mType = i;
        this.mIsSessionless = z;
        this.mValue = obj;
        this.mLoggingId = str;
        this.mQueryString = str2;
        this.mSource = mobileConfigValueSource;
    }

    public int getType() {
        return this.mType;
    }

    public boolean getIsSessionless() {
        return this.mIsSessionless;
    }

    @Nullable
    public Object getValue() {
        return this.mValue;
    }

    @Nullable
    public String getLoggingId() {
        return this.mLoggingId;
    }

    public String getQueryString() {
        return this.mQueryString;
    }

    public MobileConfigValueSource getSource() {
        return this.mSource;
    }
}
