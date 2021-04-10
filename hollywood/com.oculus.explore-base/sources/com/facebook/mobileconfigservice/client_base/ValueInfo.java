package com.facebook.mobileconfigservice.client_base;

import com.facebook.mobileconfig.factory.MobileConfigValueSource;

public class ValueInfo {
    final boolean mIsSessionless;
    final String mLoggingId;
    final String mQueryString;
    final MobileConfigValueSource mSource;
    final int mType;
    final Object mValue;

    public ValueInfo(int type, boolean isSessionless, Object value, String loggingId, String queryString) {
        this(type, isSessionless, value, loggingId, queryString, MobileConfigValueSource.UNKNOWN);
    }

    public ValueInfo(int type, boolean isSessionless, Object value, String loggingId, String queryString, MobileConfigValueSource source) {
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

    public Object getValue() {
        return this.mValue;
    }

    public String getLoggingId() {
        return this.mLoggingId;
    }

    public String getQueryString() {
        return this.mQueryString;
    }
}
