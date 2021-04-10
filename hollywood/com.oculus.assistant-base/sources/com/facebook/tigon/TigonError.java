package com.facebook.tigon;

import java.io.Serializable;

public final class TigonError implements Serializable {
    public final String mAnalyticsDetail;
    public final int mCategory;
    public final int mDomainErrorCode;
    public final String mErrorDomain;

    public TigonError(int i, String str, int i2, String str2) {
        this.mCategory = i;
        this.mErrorDomain = str;
        this.mDomainErrorCode = i2;
        this.mAnalyticsDetail = str2;
    }
}
