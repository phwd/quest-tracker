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

    SoftError(SoftErrorBuilder builder) {
        this.mCategory = builder.getCategory();
        this.mMessage = builder.getMessage();
        this.mCause = builder.getCause();
        this.mFailHarder = builder.shouldFailHarder();
        this.mSamplingFrequency = builder.getSamplingFrequency();
        this.mOnlyIfEmployeeOrBetaBuild = builder.getOnlyIfEmployeeOrBetaBuild();
    }

    public static SoftErrorBuilder newBuilder(String category, String message) {
        return new SoftErrorBuilder().setCategory(category).setMessage(message);
    }

    public static SoftError newError(SoftError softError, int newSamplingFrequency) {
        return newBuilder(softError.getCategory(), softError.getMessage()).setFailHarder(softError.shouldFailHarder()).setSamplingFrequency(newSamplingFrequency).setCause(softError.getCause()).build();
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SoftError softError = (SoftError) o;
        if (this.mFailHarder != softError.mFailHarder) {
            return false;
        }
        if (this.mOnlyIfEmployeeOrBetaBuild != softError.mOnlyIfEmployeeOrBetaBuild) {
            return false;
        }
        if (this.mSamplingFrequency != softError.mSamplingFrequency) {
            return false;
        }
        if (!equal(this.mCategory, softError.mCategory)) {
            return false;
        }
        if (!equal(this.mCause, softError.mCause)) {
            return false;
        }
        return equal(this.mMessage, softError.mMessage);
    }

    public int hashCode() {
        return hashCode(this.mCategory, this.mMessage, Boolean.valueOf(this.mFailHarder), Integer.valueOf(this.mSamplingFrequency));
    }

    public String toString() {
        return "SoftError{mCategory='" + this.mCategory + '\'' + ", mMessage='" + this.mMessage + '\'' + ", mCause=" + this.mCause + ", mFailHarder=" + this.mFailHarder + ", mSamplingFrequency=" + this.mSamplingFrequency + ", mOnlyIfEmployeeOrBetaBuild=" + this.mOnlyIfEmployeeOrBetaBuild + '}';
    }

    public static boolean equal(@Nullable Object a, @Nullable Object b) {
        return a == b || (a != null && a.equals(b));
    }

    private static int hashCode(@Nullable Object... objects) {
        return Arrays.hashCode(objects);
    }
}
