package com.facebook.common.errorreporting;

public final class SoftErrorBuilder {
    String mCategory;
    public Throwable mCause;
    boolean mFailHarder;
    String mMessage;
    boolean mOnlyIfEmployeeOrBetaBuild;
    public int mSamplingFrequency = 1000;

    SoftErrorBuilder() {
    }

    public final SoftError build() {
        return new SoftError(this);
    }
}
