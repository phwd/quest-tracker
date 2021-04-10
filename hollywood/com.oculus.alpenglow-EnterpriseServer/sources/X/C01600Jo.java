package X;

import java.util.Arrays;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.0Jo  reason: invalid class name and case insensitive filesystem */
public final class C01600Jo {
    public final int A00;
    public final String A01;
    public final String A02;
    public final Throwable A03;

    public final boolean equals(Object obj) {
        String str;
        String str2;
        Throwable th;
        Throwable th2;
        String str3;
        String str4;
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                C01600Jo r5 = (C01600Jo) obj;
                if (this.A00 != r5.A00 || (((str = this.A01) != (str2 = r5.A01) && (str == null || !str.equals(str2))) || (((th = this.A03) != (th2 = r5.A03) && (th == null || !th.equals(th2))) || ((str3 = this.A02) != (str4 = r5.A02) && (str3 == null || !str3.equals(str4)))))) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A01, this.A02, false, Integer.valueOf(this.A00)});
    }

    public final String toString() {
        return "SoftError{mCategory='" + this.A01 + '\'' + ", mMessage='" + this.A02 + '\'' + ", mCause=" + this.A03 + ", mFailHarder=" + false + ", mSamplingFrequency=" + this.A00 + ", mOnlyIfEmployeeOrBetaBuild=" + false + '}';
    }

    public C01600Jo(AnonymousClass0Jp r2) {
        this.A01 = r2.A01;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
        this.A00 = r2.A00;
    }
}
