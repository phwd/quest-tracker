package X;

import java.util.Arrays;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.0I6  reason: invalid class name */
public final class AnonymousClass0I6 {
    public final int A00;
    public final String A01;
    public final String A02;
    public final Throwable A03;
    public final boolean A04;

    public final boolean equals(Object obj) {
        String str;
        String str2;
        Throwable th;
        Throwable th2;
        String str3;
        String str4;
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                AnonymousClass0I6 r5 = (AnonymousClass0I6) obj;
                if (!(this.A04 == r5.A04 && this.A00 == r5.A00 && (((str = this.A01) == (str2 = r5.A01) || (str != null && str.equals(str2))) && (((th = this.A03) == (th2 = r5.A03) || (th != null && th.equals(th2))) && ((str3 = this.A02) == (str4 = r5.A02) || (str3 != null && str3.equals(str4))))))) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A01, this.A02, Boolean.valueOf(this.A04), Integer.valueOf(this.A00)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SoftError{mCategory='");
        sb.append(this.A01);
        sb.append('\'');
        sb.append(", mMessage='");
        sb.append(this.A02);
        sb.append('\'');
        sb.append(", mCause=");
        sb.append(this.A03);
        sb.append(", mFailHarder=");
        sb.append(this.A04);
        sb.append(", mSamplingFrequency=");
        sb.append(this.A00);
        sb.append(", mOnlyIfEmployeeOrBetaBuild=");
        sb.append(false);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass0I6(AnonymousClass0I7 r2) {
        this.A01 = r2.A01;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
        this.A04 = r2.A04;
        this.A00 = r2.A00;
    }
}
