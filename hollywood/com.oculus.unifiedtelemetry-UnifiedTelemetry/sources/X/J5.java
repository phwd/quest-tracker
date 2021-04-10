package X;

import java.util.Arrays;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class J5 {
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
                J5 j5 = (J5) obj;
                if (!(this.A04 == j5.A04 && this.A00 == j5.A00 && (((str = this.A01) == (str2 = j5.A01) || (str != null && str.equals(str2))) && (((th = this.A03) == (th2 = j5.A03) || (th != null && th.equals(th2))) && ((str3 = this.A02) == (str4 = j5.A02) || (str3 != null && str3.equals(str4))))))) {
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

    public J5(J6 j6) {
        this.A01 = j6.A01;
        this.A02 = j6.A02;
        this.A03 = j6.A03;
        this.A04 = j6.A04;
        this.A00 = j6.A00;
    }
}
