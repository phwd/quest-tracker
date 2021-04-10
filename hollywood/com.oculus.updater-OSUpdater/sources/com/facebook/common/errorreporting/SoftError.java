package com.facebook.common.errorreporting;

import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class SoftError {
    private final String mCategory;
    private final Throwable mCause;
    private final boolean mFailHarder;
    private final String mMessage;
    private final boolean mOnlyIfEmployeeOrBetaBuild;
    private final int mSamplingFrequency;

    SoftError(SoftErrorBuilder softErrorBuilder) {
        this.mCategory = softErrorBuilder.getCategory();
        this.mMessage = softErrorBuilder.getMessage();
        this.mCause = softErrorBuilder.getCause();
        this.mFailHarder = softErrorBuilder.shouldFailHarder();
        this.mSamplingFrequency = softErrorBuilder.getSamplingFrequency();
        this.mOnlyIfEmployeeOrBetaBuild = softErrorBuilder.getOnlyIfEmployeeOrBetaBuild();
    }

    public static SoftErrorBuilder newBuilder(String str, String str2) {
        return new SoftErrorBuilder().setCategory(str).setMessage(str2);
    }

    public static SoftError newError(String str, String str2) {
        return new SoftErrorBuilder().setCategory(str).setMessage(str2).build();
    }

    public static SoftError newError(String str, String str2, int i) {
        return new SoftErrorBuilder().setCategory(str).setMessage(str2).setSamplingFrequency(i).build();
    }

    public static SoftError newError(SoftError softError, int i) {
        return newBuilder(softError.getCategory(), softError.getMessage()).setFailHarder(softError.shouldFailHarder()).setSamplingFrequency(i).setCause(softError.getCause()).build();
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public Throwable getCause() {
        return this.mCause;
    }

    public boolean shouldFailHarder() {
        return this.mFailHarder;
    }

    public int getSamplingFrequency() {
        return this.mSamplingFrequency;
    }

    public boolean getOnlyIfEmployeeOrBetaBuild() {
        return this.mOnlyIfEmployeeOrBetaBuild;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SoftError softError = (SoftError) obj;
        return this.mFailHarder == softError.mFailHarder && this.mOnlyIfEmployeeOrBetaBuild == softError.mOnlyIfEmployeeOrBetaBuild && this.mSamplingFrequency == softError.mSamplingFrequency && equal(this.mCategory, softError.mCategory) && equal(this.mCause, softError.mCause) && equal(this.mMessage, softError.mMessage);
    }

    public int hashCode() {
        return hashCode(this.mCategory, this.mMessage, Boolean.valueOf(this.mFailHarder), Integer.valueOf(this.mSamplingFrequency));
    }

    public String toString() {
        return "SoftError{mCategory='" + this.mCategory + '\'' + ", mMessage='" + this.mMessage + '\'' + ", mCause=" + this.mCause + ", mFailHarder=" + this.mFailHarder + ", mSamplingFrequency=" + this.mSamplingFrequency + ", mOnlyIfEmployeeOrBetaBuild=" + this.mOnlyIfEmployeeOrBetaBuild + '}';
    }

    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int hashCode(@Nullable Object... objArr) {
        return Arrays.hashCode(objArr);
    }
}
