package com.facebook.common.errorreporting;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class SoftErrorBuilder {
    private String mCategory;
    @Nullable
    private Throwable mCause;
    private boolean mFailHarder;
    private String mMessage;
    private boolean mOnlyIfEmployeeOrBetaBuild;
    private int mSamplingFrequency = 1000;

    SoftErrorBuilder() {
    }

    public String getCategory() {
        return this.mCategory;
    }

    public SoftErrorBuilder setCategory(String str) {
        this.mCategory = str;
        return this;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public SoftErrorBuilder setMessage(String str) {
        this.mMessage = str;
        return this;
    }

    @Nullable
    public Throwable getCause() {
        return this.mCause;
    }

    public SoftErrorBuilder setCause(@Nullable Throwable th) {
        this.mCause = th;
        return this;
    }

    public boolean shouldFailHarder() {
        return this.mFailHarder;
    }

    @Deprecated
    public SoftErrorBuilder setFailHarder(boolean z) {
        this.mFailHarder = z;
        return this;
    }

    public int getSamplingFrequency() {
        return this.mSamplingFrequency;
    }

    public SoftErrorBuilder setSamplingFrequency(int i) {
        this.mSamplingFrequency = i;
        return this;
    }

    public boolean getOnlyIfEmployeeOrBetaBuild() {
        return this.mOnlyIfEmployeeOrBetaBuild;
    }

    public SoftError build() {
        return new SoftError(this);
    }
}
