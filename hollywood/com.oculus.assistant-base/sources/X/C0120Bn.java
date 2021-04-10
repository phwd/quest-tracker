package X;

import java.util.Arrays;

/* renamed from: X.Bn  reason: case insensitive filesystem */
public final class C0120Bn {
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
                C0120Bn bn = (C0120Bn) obj;
                if (this.A00 != bn.A00 || (((str = this.A01) != (str2 = bn.A01) && (str == null || !str.equals(str2))) || (((th = this.A03) != (th2 = bn.A03) && (th == null || !th.equals(th2))) || ((str3 = this.A02) != (str4 = bn.A02) && (str3 == null || !str3.equals(str4)))))) {
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
        StringBuilder sb = new StringBuilder("SoftError{mCategory='");
        sb.append(this.A01);
        sb.append('\'');
        sb.append(", mMessage='");
        sb.append(this.A02);
        sb.append('\'');
        sb.append(", mCause=");
        sb.append(this.A03);
        sb.append(", mFailHarder=");
        sb.append(false);
        sb.append(", mSamplingFrequency=");
        sb.append(this.A00);
        sb.append(", mOnlyIfEmployeeOrBetaBuild=");
        sb.append(false);
        sb.append('}');
        return sb.toString();
    }

    public C0120Bn(C0121Bo bo) {
        this.A01 = bo.A01;
        this.A02 = bo.A02;
        this.A03 = bo.A03;
        this.A00 = bo.A00;
    }
}
