package com.facebook.tigon;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.Serializable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TigonError implements Serializable {
    private final String mAnalyticsDetail;
    private final int mCategory;
    private final int mDomainErrorCode;
    private final String mErrorDomain;

    @DoNotStrip
    public TigonError(int i, String str, int i2, String str2) {
        this.mCategory = i;
        this.mErrorDomain = str;
        this.mDomainErrorCode = i2;
        this.mAnalyticsDetail = str2;
    }

    public int category() {
        return this.mCategory;
    }

    public String errorDomain() {
        return this.mErrorDomain;
    }

    public int domainErrorCode() {
        return this.mDomainErrorCode;
    }

    public String analyticsDetail() {
        return this.mAnalyticsDetail;
    }
}
