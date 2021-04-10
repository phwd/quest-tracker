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

    public ValueInfo(int type, boolean isSessionless, @Nullable Object value, @Nullable String loggingId, String queryString) {
        this(type, isSessionless, value, loggingId, queryString, MobileConfigValueSource.UNKNOWN);
    }

    public ValueInfo(int type, boolean isSessionless, @Nullable Object value, @Nullable String loggingId, String queryString, MobileConfigValueSource source) {
        this.mType = type;
        this.mIsSessionless = isSessionless;
        this.mValue = value;
        this.mLoggingId = loggingId;
        this.mQueryString = queryString;
        this.mSource = source;
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
