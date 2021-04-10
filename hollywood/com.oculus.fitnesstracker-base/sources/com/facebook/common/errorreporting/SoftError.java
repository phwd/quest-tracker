package com.facebook.common.errorreporting;

import java.util.Arrays;

public final class SoftError {
    final String mCategory;
    final Throwable mCause;
    final boolean mFailHarder;
    final String mMessage;
    final boolean mOnlyIfEmployeeOrBetaBuild;
    final int mSamplingFrequency;

    SoftError(SoftErrorBuilder softErrorBuilder) {
        this.mCategory = softErrorBuilder.mCategory;
        this.mMessage = softErrorBuilder.mMessage;
        this.mCause = softErrorBuilder.mCause;
        this.mFailHarder = softErrorBuilder.mFailHarder;
        this.mSamplingFrequency = softErrorBuilder.mSamplingFrequency;
        this.mOnlyIfEmployeeOrBetaBuild = softErrorBuilder.mOnlyIfEmployeeOrBetaBuild;
    }

    public static SoftErrorBuilder newBuilder(String str, String str2) {
        SoftErrorBuilder softErrorBuilder = new SoftErrorBuilder();
        softErrorBuilder.mCategory = str;
        softErrorBuilder.mMessage = str2;
        return softErrorBuilder;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SoftError softError = (SoftError) obj;
        return this.mFailHarder == softError.mFailHarder && this.mOnlyIfEmployeeOrBetaBuild == softError.mOnlyIfEmployeeOrBetaBuild && this.mSamplingFrequency == softError.mSamplingFrequency && equal(this.mCategory, softError.mCategory) && equal(this.mCause, softError.mCause) && equal(this.mMessage, softError.mMessage);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.mCategory, this.mMessage, Boolean.valueOf(this.mFailHarder), Integer.valueOf(this.mSamplingFrequency)});
    }

    public final String toString() {
        return "SoftError{mCategory='" + this.mCategory + '\'' + ", mMessage='" + this.mMessage + '\'' + ", mCause=" + this.mCause + ", mFailHarder=" + this.mFailHarder + ", mSamplingFrequency=" + this.mSamplingFrequency + ", mOnlyIfEmployeeOrBetaBuild=" + this.mOnlyIfEmployeeOrBetaBuild + '}';
    }

    private static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
