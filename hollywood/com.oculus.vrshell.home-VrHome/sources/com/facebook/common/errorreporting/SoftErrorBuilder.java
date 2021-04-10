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

    public SoftErrorBuilder setCategory(String category) {
        this.mCategory = category;
        return this;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public SoftErrorBuilder setMessage(String message) {
        this.mMessage = message;
        return this;
    }

    @Nullable
    public Throwable getCause() {
        return this.mCause;
    }

    public SoftErrorBuilder setCause(@Nullable Throwable cause) {
        this.mCause = cause;
        return this;
    }

    public boolean shouldFailHarder() {
        return this.mFailHarder;
    }

    @Deprecated
    public SoftErrorBuilder setFailHarder(boolean failHarder) {
        this.mFailHarder = failHarder;
        return this;
    }

    public int getSamplingFrequency() {
        return this.mSamplingFrequency;
    }

    public SoftErrorBuilder setSamplingFrequency(int samplingFrequency) {
        this.mSamplingFrequency = samplingFrequency;
        return this;
    }

    public boolean getOnlyIfEmployeeOrBetaBuild() {
        return this.mOnlyIfEmployeeOrBetaBuild;
    }

    public SoftError build() {
        return new SoftError(this);
    }
}
