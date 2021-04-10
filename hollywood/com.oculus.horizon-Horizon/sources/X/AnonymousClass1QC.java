package X;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1QC  reason: invalid class name */
public final class AnonymousClass1QC extends Exception {
    public final long mBytesTransferred;
    public final String mFailureReason;
    @Nullable
    public final Exception mInnerException;
    public final boolean mIsCancellation;
    public final boolean mIsRetriable;
    @Nullable
    public final AnonymousClass1Rk mRequestMethod;

    public final String getMessage() {
        String str;
        String str2;
        String str3 = this.mFailureReason;
        if (this.mIsCancellation) {
            str = " (Cancellation), ";
        } else {
            str = ", ";
        }
        Exception exc = this.mInnerException;
        if (exc != null) {
            str2 = exc.getMessage();
        } else {
            str2 = "None";
        }
        return AnonymousClass006.A09("Failure Reason: ", str3, str, "InnerException: ", str2);
    }

    public AnonymousClass1QC(String str, long j, boolean z, @Nullable Exception exc, boolean z2, @Nullable AnonymousClass1Rk r7) {
        super(str, exc);
        this.mFailureReason = str;
        this.mBytesTransferred = j;
        this.mIsCancellation = z;
        this.mInnerException = exc;
        this.mIsRetriable = z2;
        this.mRequestMethod = r7;
    }
}
