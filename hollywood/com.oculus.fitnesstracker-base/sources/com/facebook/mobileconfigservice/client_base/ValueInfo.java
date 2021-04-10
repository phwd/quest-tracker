package com.facebook.mobileconfigservice.client_base;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigValueSource;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ValueInfo {
    public final boolean mIsSessionless;
    public final String mLoggingId;
    public final String mQueryString;
    final MobileConfigValueSource mSource;
    public final int mType;
    public final Object mValue;

    public ValueInfo(int i, boolean z, Object obj, String str, String str2) {
        this(i, z, obj, str, str2, MobileConfigValueSource.UNKNOWN);
    }

    public ValueInfo(int i, boolean z, Object obj, String str, String str2, MobileConfigValueSource mobileConfigValueSource) {
        this.mType = i;
        this.mIsSessionless = z;
        this.mValue = obj;
        this.mLoggingId = str;
        this.mQueryString = str2;
        this.mSource = mobileConfigValueSource;
    }
}
